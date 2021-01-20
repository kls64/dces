package com.hust.dces.Mapper;

import com.hust.dces.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    @Insert("insert into user (username,password,email,phone) values(#{username},#{password},#{email},#{phone});")
    Integer addUserInfo(User user);

}
