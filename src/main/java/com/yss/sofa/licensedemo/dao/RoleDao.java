package com.yss.sofa.licensedemo.dao;

import com.yss.sofa.licensedemo.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleDao {
    @Select("select * from role where id in(select roleid from user_role where userid=#{userId})")
    Set<Role> findRoles(Long userId);
}
