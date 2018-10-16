package com.yss.sofa.licensedemo.web;

import com.yss.sofa.licensedemo.domain.SecurityUser;
import com.yss.sofa.licensedemo.domain.User;
import com.yss.sofa.licensedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/find")
    public User findUser(@RequestParam String username, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        System.out.println("current user="+user.getUsername());
        return userService.findByUsername(username);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }
}
