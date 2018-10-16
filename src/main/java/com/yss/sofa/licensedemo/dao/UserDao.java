package com.yss.sofa.licensedemo.dao;

import com.yss.sofa.licensedemo.domain.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    @Select("select * from user where username = #{username}")
    User findOne(String username);

    @Insert("INSERT INTO USER(username, password, status,remark) VALUES(#{username}, #{password}, #{status}, #{remark})")
    int insert(User user);

    @Update("UPDATE user SET remark=#{remark} WHERE username=#{username}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Long id);


    /**
     * 对于多个参数来说，每个参数之前都要加上@Param注解，
     * 要不然会找不到对应的参数进而报错
     */
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(@Param("username")String username, @Param("password")String password);

}
