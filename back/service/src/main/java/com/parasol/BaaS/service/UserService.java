package com.parasol.BaaS.service;

import com.parasol.BaaS.api_model.UserInfo;
import com.parasol.BaaS.api_request.LoginRequest;
import com.parasol.BaaS.api_request.UserRegisterRequest;
import com.parasol.BaaS.api_request.UserUpdateRequest;
import com.parasol.BaaS.db.entity.User;
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
