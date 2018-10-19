package com.yss.sofa.licensedemo.service.impl;

import com.yss.sofa.licensedemo.dao.UserDao;
import com.yss.sofa.licensedemo.dao.UserRoleDao;
import com.yss.sofa.licensedemo.domain.User;
import com.yss.sofa.licensedemo.domain.UserRole;
import com.yss.sofa.licensedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
//相当于ehcache.xml文件里面的<cache name="user">
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    //在方法执行前Spring先是否有缓存数据，如果有直接返回。如果没有数据，调用方法并将方法返回值存放在缓存当中。
    @Cacheable
    public User findByUsername(String username) {
        return userDao.findOne(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User saveOrUpdate(User user) {
        User eqUser = userDao.findOne(user.getUsername());
        if (eqUser != null){
            throw new RuntimeException("用户已经存在,不能重复添加");
        }

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus("0");
        user.setRemark("普通用户");
        int n =  userDao.insert(user);
        if (n<1){
            throw new RuntimeException("save error");
        }
        UserRole userRole = new UserRole();
        userRole.setUserid(user.getId());
        userRole.setRoleid(2L);//普通用户
        userRoleDao.insert(userRole);
        return user;
    }

    @Override
    public User cancel(Long id) {
        User user = new User();
        user.setId(id);
        user.setStatus("1");
        user.setRemark("注销用户");
        int n =  userDao.update(user);
        if (n<1){
            throw new RuntimeException("update error");
        }
        return user;
    }

    @Override
    public User resetPassword(Long id) {
        User user = userDao.findById(id);
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        int n =  userDao.reset(user);
        if (n<1){
            throw new RuntimeException("update error");
        }
        return user;
    }

}
