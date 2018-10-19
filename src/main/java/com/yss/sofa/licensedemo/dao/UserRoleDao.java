package com.yss.sofa.licensedemo.dao;

import com.yss.sofa.licensedemo.domain.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao {
    @Insert("INSERT INTO USER_ROLE(userid,roleid) VALUES(#{userid}, #{roleid})")
    int insert(UserRole userRole);
}
