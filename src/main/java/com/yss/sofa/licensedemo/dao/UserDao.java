package com.yss.sofa.licensedemo.dao;

import com.yss.sofa.licensedemo.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where username = #{username}")
    User findOne(String username);

    @Insert("INSERT INTO USER(username, password, status,remark) VALUES(#{username}, #{password}, #{status}, #{remark})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insert(User user);

    @Update("UPDATE user SET status=#{status}, remark=#{remark} WHERE id =#{id}")
    int update(User user);

    @Update("UPDATE user SET password=#{password} WHERE id =#{id}")
    int reset(User user);

    @Delete("DELETE TABLE USER WHERE id =#{id}")
    void delete(Long id);

    @Select("select * from user where id = #{id}")
    User findById(Long id);
}
