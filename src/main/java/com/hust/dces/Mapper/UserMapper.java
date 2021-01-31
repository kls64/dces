package com.hust.dces.Mapper;

import com.hust.dces.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface UserMapper {

    @Insert("insert into user (username,password,email,phone,typeid) values(#{username},#{password},#{email},#{phone},#{typeid});")
    Integer addUserInfo(User user);

    @Select("select * from user where username=#{username} and password=#{password}")
    User loginuser(String username, String password);

    @Select("select * from user where userid=#{userid}")
    List<User> checkUserByUserID(Integer userID);

    @Select("select * from user where userid=#{userid}")
    User findUserByUserID(Integer userID);

    @Update("update user set username=#{username},password=#{password},email=#{email},phone=#{phone} where userid=#{userid};")
    Integer updateUserByID(User user);
}
