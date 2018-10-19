package com.yss.sofa.licensedemo.web;

import com.yss.sofa.licensedemo.domain.User;
import com.yss.sofa.licensedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


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

    /**
     * 页面获取用户列表
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Model model) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("user/list", "userModel", model);
    }
    /**
     * 获取创建表单页面
     *
     * @param model
     * @return
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("user/form", "userModel", model);
    }
    /**
     * 保存用戶
     * @param user
     * @return
   */
    @PostMapping("/submit")
    public ModelAndView saveOrUpdateUser(User user) {
        user = userService.saveOrUpdate(user);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:list");// 重定向至list映射方法
        return mav;
    }

    /**
     * 注销用户
     * @param id
     * @param mav
     * @return
     */
    @GetMapping("/cancel/{id}")
    public ModelAndView cancelUser(@PathVariable("id") Long id, ModelAndView mav){
        if (id.equals(1L)){
            throw new RuntimeException("不能注销超级管理员");
        }
        userService.cancel(id);
        mav.setViewName("redirect:/user/list");// 重定向至list映射方法
        return mav;
    }

    @GetMapping("/reset/{id}")
    public ModelAndView resetPassword(@PathVariable("id") Long id, ModelAndView mav){
        userService.resetPassword(id);
        mav.setViewName("redirect:/user/list");// 重定向至list映射方法
        return mav;
    }

}
