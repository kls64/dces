package com.hust.dces.Mapper;

import com.hust.dces.Entity.Appealdoc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AppealMapper {

    @Insert("insert into appealdoc(userid,docname,reason,appealtime,feedback,feedbacktime) values(#{userid},#{docname},#{reason},#{appealtime},#{feedback},#{feedbacktime})")
    Integer addAppeal(Appealdoc appealdoc);

    @Update("update appealdoc set status=#{status}, feedback=#{feedback}, feedbacktime=#{feedbacktime} where appealid=#{appealid};")
    Integer updateAppealByID(Appealdoc appealdoc);

    // @Select("select * from appealdoc where docname=#{docname}")
    // Appealdoc findAppealByDocname(String docname);

    @Select("select * from appealdoc where appealid=#{appealid}")
    Appealdoc findAppealByID(Integer appealid);

    @Select("select apl.*, usr.username from user usr join appealdoc apl on usr.userid=apl.userid")
    List<Appealdoc> findAllAppeal();

    @Select("select * from appealdoc where userid=#{userid}")
    List<Appealdoc> findAppealByUserId(Integer userId);

    // @Select("select * from document where userid =#{userid}")
    // Document discoverDocumentByUserId(Integer userId);
}
