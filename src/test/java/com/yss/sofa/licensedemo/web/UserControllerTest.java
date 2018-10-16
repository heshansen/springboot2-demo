package com.yss.sofa.licensedemo.web;

import com.yss.sofa.licensedemo.dao.UserDao;
import com.yss.sofa.licensedemo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void userCURD() throws Exception {
        User newUser = new User();
        newUser.setUsername("admin");
        newUser.setPassword("123456");
        newUser.setStatus("0");
        newUser.setRemark("testUser");
        // insert一条数据，并select出来验证
        userDao.insert(newUser);
        User user = userDao.findOne(newUser.getUsername());
        Assert.assertEquals("testUser",user.getRemark());
        // update一条数据，并select出来验证
        user.setRemark("updateUser");
        userDao.update(user);
        user = userDao.findOne(newUser.getUsername());
        Assert.assertEquals("updateUser",user.getRemark());
        // 删除这条数据，并select验证
        userDao.delete(user.getId());
        user = userDao.findOne(newUser.getUsername());
        Assert.assertEquals(null,user);
    }
}