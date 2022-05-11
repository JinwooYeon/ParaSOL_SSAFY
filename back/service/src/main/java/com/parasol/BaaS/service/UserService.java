package com.parasol.BaaS.service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.parasol.BaaS.api_model.AuthToken;
import com.parasol.BaaS.api_model.Password;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.auth.jwt.JwtAuthenticationFilter;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.auth.jwt.util.JwtTokenUtil;
import com.parasol.BaaS.db.entity.Token;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.TokenRepository;
import com.parasol.BaaS.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthToken login(LoginRequest request) {
        String id = request.getId();
        String password = request.getPassword();

        User user = getUserByUserId(id);

        if(user == null) {
            return null;
        }

        if(passwordEncoder.matches(password, user.getUserPassword())) {
            AuthToken authToken = JwtTokenUtil.getToken(id);
            String refreshToken = authToken.getRefreshToken().getRefreshToken();

            Optional<Token> checkToken = tokenRepository.findByUser_UserId(id);

            if(checkToken.isPresent()) {
                Token newToken = checkToken.get();
                newToken.setRefreshToken(refreshToken);
                tokenRepository.save(newToken);
            } else {
                Token token = Token.builder()
                        .user(user)
                        .refreshToken(refreshToken)
                        .build();
                tokenRepository.save(token);
            }
            return authToken;
        }
        return null;
    }

    public AuthToken loginOauth(String id) {
        User user = getUserByUserId(id);

        if(user == null) {
            return null;
        }

        AuthToken authToken = JwtTokenUtil.getToken(id);
        String refreshToken = authToken.getRefreshToken().getRefreshToken();

        Optional<Token> checkToken = tokenRepository.findByUser_UserId(id);

        if(checkToken.isPresent()) {
            Token newToken = checkToken.get();
            newToken.setRefreshToken(refreshToken);
            tokenRepository.save(newToken);
        } else {
            Token token = Token.builder()
                    .user(user)
                    .refreshToken(refreshToken)
                    .build();
            tokenRepository.save(token);
        }
        return authToken;
    }

    public Password resetPassword(PasswordResetRequest request) {
        String id = request.getId();
        String name = request.getName();

        if(!StringUtils.hasText(id) || !StringUtils.hasText(name)) {
            return null;
        }

        Optional<User> oUser = userRepository.findByUserId(id);

        if(!oUser.isPresent()) {
            return null;
        }

        User user = oUser.get();
        if(name.equals(user.getUserName())) {
            String newPassword = createPassword();

            user.setUserPassword(passwordEncoder.encode(newPassword));

            userRepository.save(user);

            return Password.builder()
                    .password(newPassword)
                    .build();
        }
        return null;
    }

    // AuthToken 재발급
    public AuthToken reissueAuthToken(String id, String refreshToken) {
        Optional<Token> checkToken = tokenRepository.findByUser_UserId(id);

        if(!checkToken.isPresent()) {
            return null;
        }

        Token token = checkToken.get();
        String originRefreshToken = token.getRefreshToken();
        String message = JwtTokenUtil.handleError(originRefreshToken);

        if("success".equals(message)) {
            if(originRefreshToken.equals(refreshToken)) {
                AuthToken authToken = JwtTokenUtil.getToken(id);
                String newRefreshToken = authToken.getRefreshToken().getRefreshToken();
                token.setRefreshToken(newRefreshToken);

                tokenRepository.save(token);
                return authToken;
            }
        }
        return null;
    }


    public User getUserByUserId(String id) {
        Optional<User> oUser = userRepository.findByUserId(id);
        if(!oUser.isPresent()) return null;
        User user = oUser.get();

        return user;
    }

    public User createUser(UserRegisterRequest request) {
        User user = User.builder()
                .userId(request.getId())
                .userPassword(passwordEncoder.encode(request.getPassword()))
                .userName(request.getName())
                .build();

        return userRepository.save(user);
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findByUserId(userId).get();

        if(StringUtils.hasText(request.getName())) {
            user.setUserName(request.getName());
        }

        // TODO : 비밀번호 암호화
        if(StringUtils.hasText(request.getPassword())) {
            user.setUserPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.save(user);
    }

    @Transactional
    public boolean deleteUser(String userId) {
        // refreshToken 먼저 삭제 -> user 삭제
        Long tokenDelete = tokenRepository.deleteByUserUserId(userId);

        Long delete = userRepository.deleteByUserId(userId);
        if(tokenDelete > 0 && delete > 0) return true;
        return false;
    }

    // 임시 비밀번호 8자리
    public String createPassword() {
        StringBuffer password = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    password.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    password.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    password.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return password.toString();
    }

}
