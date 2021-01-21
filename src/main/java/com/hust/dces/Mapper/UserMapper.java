package com.hust.dces.Mapper;

import com.hust.dces.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {

    @Insert("insert into user (username,password,email,phone) values(#{username},#{password},#{email},#{phone});")
    Integer addUserInfo(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    User loginuser(String username, String password);
}
