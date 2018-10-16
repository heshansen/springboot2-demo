package com.yss.sofa.licensedemo.service.impl;

import com.yss.sofa.licensedemo.dao.UserDao;
import com.yss.sofa.licensedemo.domain.User;
import com.yss.sofa.licensedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
//相当于ehcache.xml文件里面的<cache name="user">
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    //在方法执行前Spring先是否有缓存数据，如果有直接返回。如果没有数据，调用方法并将方法返回值存放在缓存当中。
    @Cacheable
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

}
