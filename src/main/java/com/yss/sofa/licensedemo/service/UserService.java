package com.yss.sofa.licensedemo.service;

import com.yss.sofa.licensedemo.domain.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> findAll();

    User saveOrUpdate(User user);

    User cancel(Long id);

    User resetPassword(Long id);
}
