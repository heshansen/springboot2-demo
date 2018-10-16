package com.yss.sofa.licensedemo.config;

import com.yss.sofa.licensedemo.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //指定密码加密所使用的加密器为passwordEncoder()
        // 需要将密码加密后写入数据库
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.eraseCredentials(false);
        //在内存中创建了一个用户，该用户的名称为user，密码为password，用户角色为ADMIN
//        auth
//                .inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password(new BCryptPasswordEncoder().encode("000000"))
//                .roles("ADMIN");
    }

    /**
     * 通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。
     * 1.指定了/和/home不需要任何认证就可以访问，其他的路径都必须通过身份验证。
     * 2.通过formLogin()定义当需要用户登录时候，转到的登录页面。
     *
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/hello").permitAll()
//                .antMatchers("/user/**").hasRole("ROLE_ADMIN")  //此处要把 ROLE_ 去除
                .antMatchers("/user/*").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                //登陆之后拥有“admin”权限才可以访问/user接口，否则系统会出现“403”权限不足的提示
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf() //关闭csrf 不然不支持post
                .disable();

    }

}
