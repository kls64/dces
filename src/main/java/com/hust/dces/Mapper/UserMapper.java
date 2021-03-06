package com.hust.dces.Mapper;

import com.hust.dces.Entity.Document;
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

    @Select("select count(*) from document where userid = #{userID}")
    Integer getFileCountByUserID(Integer userID);

    @Select("select count(*) from appealdoc where userid = #{userID}")
    Integer getAppealCountByUserID(Integer userID);

    @Select("select user.username, document.docname, document.uploadtime, document.doctype, document.status from user join document on user.userid = document.userid")
    List<Document>findDocByUser();

}
