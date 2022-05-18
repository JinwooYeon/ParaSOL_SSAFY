package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.AccessToken;
import com.parasol.BaaS.api_model.AuthToken;
import com.parasol.BaaS.api_model.Password;
import com.parasol.BaaS.api_model.RefreshToken;
import com.parasol.BaaS.api_request.*;
import com.parasol.BaaS.api_response.*;
import com.parasol.BaaS.auth.jwt.UserDetail;
import com.parasol.BaaS.auth.jwt.util.JwtTokenUtil;
import com.parasol.BaaS.db.entity.PayHistory;
import com.parasol.BaaS.db.entity.PayLedger;
import com.parasol.BaaS.db.entity.Token;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankConnectionRepository bankConnectionRepository;

    @Autowired
    private BioInfoRepository bioInfoRepository;

    @Autowired
    private PayLedgerRepository payLedgerRepository;

    @Autowired
    private PayHistoryRepository payHistoryRepository;

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = getUserByUserId(id);

        if (!passwordEncoder.matches(password, user.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByUserId(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findByUserId(id);
        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = getUserByUserId(id);

        if (!name.equals(user.getUserName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR); });

        String oldRefreshToken = savedToken.getRefreshToken();
        String message = JwtTokenUtil.handleError(oldRefreshToken);

        if(!StringUtils.hasText(message)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!message.equals("success")) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!StringUtils.hasText(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .userId(id)
                .userPassword(passwordEncoder.encode(password))
                .userName(name)
                .build();

        userRepository.save(user);

        PayLedger payLedger = PayLedger.builder()
                .owner(user)
                .balance(0L)
                .build();

        payLedgerRepository.save(payLedger);

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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(!StringUtils.hasText(newPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        User user = getUserByUserId(id);

        if (!passwordEncoder.matches(password, user.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (passwordEncoder.matches(newPassword, user.getUserPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
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
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "give me a token");
        }

        UserDetail userDetail = (UserDetail) authentication.getDetails();
        String id = userDetail.getUsername();

        Long bankConnectionDeleteResult = bankConnectionRepository.deleteByUser_UserId(id);
        Long bioInfoDeleteResult = bioInfoRepository.deleteByOwnerUserId(id);
        Long payHistoryDeleteResult = payHistoryRepository.deleteByUser_UserId(id);
        Long payLedgerDeleteResult = payLedgerRepository.deleteByOwnerUserId(id);
        Long tokenDeleteResult = tokenRepository.deleteByUserUserId(id);
        Long userDeleteResult = userRepository.deleteByUserId(id);

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
                .orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR); });
    }

}
