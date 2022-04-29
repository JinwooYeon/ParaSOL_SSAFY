package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.LoginRequest;
import com.parasol.BaaS.api_request.UserRegisterRequest;
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

    public User createUser(UserRegisterRequest request) {
        // TODO : 비밀번호 암호화
        User user = User.builder()
                .userId(request.getId())
                .userPassword(request.getPassword())
                .userName(request.getName())
                .build();

        return userRepository.save(user);
    }

}
