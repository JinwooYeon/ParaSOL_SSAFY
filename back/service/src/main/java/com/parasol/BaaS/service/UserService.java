package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.AuthToken;
import com.parasol.BaaS.api_model.LoginInfo;
import com.parasol.BaaS.api_model.RefreshToken;
import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.LoginRequest;
import com.parasol.BaaS.api_request.UserRegisterRequest;
import com.parasol.BaaS.api_request.UserUpdateRequest;
import com.parasol.BaaS.auth.jwt.util.JwtTokenUtil;
import com.parasol.BaaS.db.entity.Token;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.TokenRepository;
import com.parasol.BaaS.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    public AuthToken login(LoginRequest request) {
        String id = request.getId();
        String password = request.getPassword();

        User user = getUserByUserId(id);

        if(user == null) {
            return null;
        }

        if(password.equals(user.getUserPassword())) {
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

    // 새로운 AuthToken 발급
    public AuthToken issueAuthToken(String id, String refreshToken) {
        Optional<Token> checkToken = tokenRepository.findByUser_UserId(id);

        if(!checkToken.isPresent()) return null;

        Token token = checkToken.get();

        JwtTokenUtil.handleError(refreshToken);

        if(refreshToken.equals(token.getRefreshToken())) {
            AuthToken authToken = JwtTokenUtil.getToken(id);
            String newRefreshToken = authToken.getRefreshToken().getRefreshToken();
            token.setRefreshToken(newRefreshToken);

            tokenRepository.save(token);
            return authToken;
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
        // TODO : 비밀번호 암호화
        User user = User.builder()
                .userId(request.getId())
                .userPassword(request.getPassword())
                .userName(request.getName())
                .build();

        return userRepository.save(user);
    }

    public User updateUser(UserUpdateRequest request) {
        String userId = request.getId();
        User user = userRepository.findByUserId(userId).get();

        if(StringUtils.hasText(request.getName())) {
            user.setUserName(request.getName());
        }

        // TODO : 비밀번호 암호화
        if(StringUtils.hasText(request.getPassword())) {
            user.setUserPassword(request.getPassword());
        }

        return userRepository.save(user);
    }

    @Transactional
    public boolean deleteUser(String userId) {
        Long delete = userRepository.deleteByUserId(userId);
        if(delete > 0) return true;
        return false;
    }
}
