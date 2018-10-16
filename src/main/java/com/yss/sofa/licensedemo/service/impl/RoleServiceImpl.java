package com.yss.sofa.licensedemo.service.impl;

import com.yss.sofa.licensedemo.dao.RoleDao;
import com.yss.sofa.licensedemo.domain.Role;
import com.yss.sofa.licensedemo.domain.User;
import com.yss.sofa.licensedemo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Set<Role> findRoles(Long userId) {
        return roleDao.findRoles(userId);
    }
}
