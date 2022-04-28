package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.LoginRequest;
import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserInfo getUserByUserId(String id) {

        Optional<User> oUser = userRepository.findByUserId(id);
        if(!oUser.isPresent()) return null;
        User user = oUser.get();

        return UserInfo.builder()
                .id(user.getUserId())
                .password(user.getUserPassword())
                .name(user.getUserName())
                .build();
    }

}
