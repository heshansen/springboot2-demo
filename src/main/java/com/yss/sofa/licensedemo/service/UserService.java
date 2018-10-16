package com.yss.sofa.licensedemo.service;

import com.yss.sofa.licensedemo.domain.User;

public interface UserService {
    User findByUsername(String username);

    User insert(User user);
}
