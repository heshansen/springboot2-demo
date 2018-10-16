package com.yss.sofa.licensedemo.service;

import com.yss.sofa.licensedemo.domain.Role;
import com.yss.sofa.licensedemo.domain.User;

import java.util.Set;

public interface RoleService {
    Set<Role> findRoles(Long userId);
}
