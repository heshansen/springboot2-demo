package com.yss.sofa.licensedemo.service;

import com.yss.sofa.licensedemo.domain.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> findRoles(Long userId);
}
