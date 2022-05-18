package com.parasol.BaaS.auth.oauth;

import com.parasol.BaaS.db.entity.PayLedger;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.PayLedgerRepository;
import com.parasol.BaaS.db.repository.TokenRepository;
import com.parasol.BaaS.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRequestMapper userRequestMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PayLedgerRepository payLedgerRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        User oUser = userRequestMapper.toUser(oAuth2User);

        String id = oUser.getUserId();
        String name = oUser.getUserName();
        
        Optional<User> checkUser = userRepository.findByUserId(id);

        // 회원가입이 안 되어있는 경우 -> 회원 가입 (페이도 동시 생성)
        if(!checkUser.isPresent()) {
            User user = User.builder()
                    .userId(id)
                    .userName(name)
                    .build();

            userRepository.save(user);

            PayLedger payLedger = PayLedger.builder()
                    .owner(user)
                    .balance(Long.valueOf(0))
                    .build();

            payLedgerRepository.save(payLedger);
        } else {
            User user = checkUser.get();
            user.setUserName(name);
            userRepository.save(user);
        }

        String baseUrl = ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();

        String redirectUri = UriComponentsBuilder.fromUriString(baseUrl + "/user/login/oauth")
                .queryParam("id", id)
                .toUriString();
        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }
}
