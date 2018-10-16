package com.yss.sofa.licensedemo.service;

import com.yss.sofa.licensedemo.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    User insert(User user);
}
