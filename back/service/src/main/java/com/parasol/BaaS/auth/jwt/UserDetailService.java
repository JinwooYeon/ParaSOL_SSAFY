package com.parasol.BaaS.auth.jwt;

import com.parasol.BaaS.db.entity.User;
import com.parasol.BaaS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailService implements UserDetailsService{
	UserService userService;

	@Autowired
	@Lazy
	public UserDetailService(UserService userService){
		this.userService = userService;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		User user = userService.getUserByUserId(username);
    		if(user != null) {
    			UserDetail userDetails = new UserDetail(user);
    			return userDetails;
    		}
    		return null;
    }
}