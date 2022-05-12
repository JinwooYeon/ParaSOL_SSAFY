package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.AccessToken;
import com.parasol.BaaS.api_model.AuthToken;
import com.parasol.BaaS.api_model.Password;
import com.parasol.BaaS.api_model.RefreshToken;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.auth.jwt.util.JwtTokenUtil;
import com.parasol.BaaS.db.entity.Token;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.TokenRepository;
import com.parasol.BaaS.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
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

    public Mono<LoginResponse> login(
            LoginRequest request
    ) throws IllegalArgumentException, NoSuchElementException {
        String id = request.getId();
        String password = request.getPassword();

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException();
        }

        User user = getUserByUserId(id);

        if (!passwordEncoder.matches(password, user.getUserPassword())) {
            throw new IllegalArgumentException();
        }

        AuthToken newToken = JwtTokenUtil.getToken(id);
        String newAccessToken = newToken.getAccessToken().getAccessToken();
        String newRefreshToken = newToken.getRefreshToken().getRefreshToken();

        Token savedToken = tokenRepository.findByUser_UserId(id)
                .orElse(
                        Token.builder()
                                .user(user)
                                .refreshToken(newRefreshToken)
                                .build()
                );
        savedToken.setRefreshToken(newRefreshToken);
        tokenRepository.save(savedToken);

        return Mono.just(
                LoginResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build()
        );
    }

    public Mono<LoginResponse> loginOauth(
            LoginRequest request
    ) throws IllegalArgumentException, NoSuchElementException {
        String id = request.getId();

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        User user = getUserByUserId(id);

        AuthToken newToken = JwtTokenUtil.getToken(id);
        String newAccessToken = newToken.getAccessToken().getAccessToken();
        String newRefreshToken = newToken.getRefreshToken().getRefreshToken();

        Token savedToken = tokenRepository.findByUser_UserId(id)
                .orElse(
                        Token.builder()
                                .user(user)
                                .refreshToken(newRefreshToken)
                                .build()
                );
        savedToken.setRefreshToken(newRefreshToken);
        tokenRepository.save(savedToken);

        return Mono.just(
                LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build()
        );
    }

    public Mono<IdCheckResponse> idCheck(
            IdCheckRequest request
    ) throws IllegalArgumentException {
        String id = request.getId();

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        Optional<User> user = userRepository.findByUserId(id);
        if (user.isPresent()) {
            throw new IllegalArgumentException();
        }

        return Mono.just(
                IdCheckResponse.builder()
                        .build()
        );
    }

    public Mono<PasswordResetResponse> resetPassword(
            PasswordResetRequest request
    ) throws IllegalArgumentException, NoSuchElementException {
        String id = request.getId();
        String name = request.getName();

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException();
        }

        User user = getUserByUserId(id);

        if (!name.equals(user.getUserName())) {
            throw new IllegalArgumentException();
        }

        String newPassword = createPassword();
        user.setUserPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return Mono.just(
                PasswordResetResponse.builder()
                        .password(newPassword)
                        .build()
        );
    }

    // AuthToken 재발급
    public Mono<ReissueTokenResponse> reissueAuthToken(
            ReissueTokenRequest request
    ) throws IllegalStateException, IllegalArgumentException, AccessDeniedException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();

        String id = userDetail.getUsername();
        String refreshToken = authentication.getPrincipal().toString();

        Token savedToken = tokenRepository.findByUser_UserId(id)
                .orElseThrow(NoSuchElementException::new);

        String oldRefreshToken = savedToken.getRefreshToken();
        String message = JwtTokenUtil.handleError(oldRefreshToken);

        if(!StringUtils.hasText(message)) {
            throw new IllegalStateException();
        }

        if (!message.equals("success")) {
            throw new IllegalStateException();
        }

        if (!oldRefreshToken.equals(refreshToken)) {
            throw new IllegalStateException();
        }

        AuthToken newToken = JwtTokenUtil.getToken(id);
        String newAccessToken = newToken.getAccessToken().getAccessToken();
        String newRefreshToken = newToken.getRefreshToken().getRefreshToken();

        savedToken.setRefreshToken(newRefreshToken);
        tokenRepository.save(savedToken);

        return Mono.just(
                ReissueTokenResponse.builder()
                        .accessToken(newAccessToken)
                        .refreshToken(newRefreshToken)
                        .build()
        );
    }

    public Mono<QueryUserInfoResponse> getUserInfo(
            QueryUserInfoRequest request
    ) throws IllegalStateException, NoSuchElementException, AccessDeniedException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        if (!StringUtils.hasText(id)) {
            throw new IllegalStateException();
        }

        User user = getUserByUserId(id);

        return Mono.just(
                QueryUserInfoResponse.builder()
                        .id(user.getUserId())
                        .name(user.getUserName())
                        .build()
        );
    }

    public Mono<RegisterResponse> createUser(
            RegisterRequest request
    ) throws IllegalArgumentException {
        String id = request.getId();
        String password = request.getPassword();
        String name = request.getName();

        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException();
        }

        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException();
        }

        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException();
        }

        User user = User.builder()
                .userId(id)
                .userPassword(passwordEncoder.encode(password))
                .userName(name)
                .build();

        userRepository.save(user);

        return Mono.just(
                RegisterResponse.builder()
                        .id(user.getUserId())
                        .name(user.getUserName())
                        .build()
        );
    }

    public Mono<UpdateResponse> updateUser(
            PasswordUpdateRequest request
    ) throws IllegalArgumentException, NoSuchElementException, AccessDeniedException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        String password = request.getPassword();
        String newPassword = request.getNewPassword();

        if(!StringUtils.hasText(password)) {
            throw new IllegalArgumentException();
        }

        if(!StringUtils.hasText(newPassword)) {
            throw new IllegalArgumentException();
        }

        User user = getUserByUserId(id);

        if (!passwordEncoder.matches(password, user.getUserPassword())) {
            throw new IllegalArgumentException();
        }

        if (passwordEncoder.matches(newPassword, user.getUserPassword())) {
            throw new IllegalArgumentException();
        }

        user.setUserPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return Mono.just(
                UpdateResponse.builder()
                        .id(user.getUserId())
                        .name(user.getUserName())
                        .build()
        );
    }

    @Transactional
    public Mono<DeleteResponse> deleteUser(
            DeleteRequest request
    ) throws IllegalStateException, AccessDeniedException {
        Authentication authentication = request.getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        Long tokenDeleteResult = tokenRepository.deleteByUserUserId(id);

        if (tokenDeleteResult <= 0) {
            throw new IllegalStateException();
        }

        Long userDeleteResult = userRepository.deleteByUserId(id);

        if (userDeleteResult <= 0) {
            throw new IllegalStateException();
        }

        return Mono.just(
                DeleteResponse.builder()
                        .build()
        );
    }

    // 임시 비밀번호 8자리
    public String createPassword(
    ) {
        StringBuilder password = new StringBuilder();
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

    public User getUserByUserId(
            String id
    ) throws NoSuchElementException {
        return userRepository.findByUserId(id)
                .orElseThrow(NoSuchElementException::new);
    }

}
