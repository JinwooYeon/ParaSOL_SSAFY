package com.parasol.BaaS.auth.oauth;

import com.parasol.BaaS.db.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRequestMapper {
    public User toUser(OAuth2User oAuth2User) {
        Map<String, Object> map = oAuth2User.getAttributes();

        return User.builder()
                .userId(map.get("email").toString())
                .userName(map.get("name").toString())
                .build();
    }
}
