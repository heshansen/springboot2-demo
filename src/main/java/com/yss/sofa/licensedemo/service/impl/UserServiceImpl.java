package com.yss.sofa.licensedemo.service.impl;

import com.yss.sofa.licensedemo.dao.UserDao;
import com.yss.sofa.licensedemo.domain.User;
import com.yss.sofa.licensedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findOne(username);
    }

    @Override
    public User insert(User user) {
        int n =  userDao.insert(user);
        System.out.println("insert return n="+n);
        if (n>0){
            return user;
        }
        throw new RuntimeException("insert error");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
