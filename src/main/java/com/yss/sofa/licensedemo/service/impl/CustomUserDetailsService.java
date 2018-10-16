package com.yss.sofa.licensedemo.service.impl;

import com.yss.sofa.licensedemo.domain.Role;
import com.yss.sofa.licensedemo.domain.SecurityUser;
import com.yss.sofa.licensedemo.service.RoleService;
import com.yss.sofa.licensedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用User中的username作为用户名:
        com.yss.sofa.licensedemo.domain.User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        Set<Role> roles = roleService.findRoles(user.getId());
        SecurityUser sUser = new SecurityUser(user, roles);
        return sUser;
    }
}
