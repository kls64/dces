package com.hust.dces.Mapper;

import com.hust.dces.Entity.Appealdoc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppealMapper {

    @Insert("insert into appealdoc(userid,docname,reason,appealtime) values(#{userid},#{docname},#{reason},#{appealtime})")
    Integer addAppeal(Appealdoc appealdoc);

    // @Select("select * from document where userid =#{userid}")
    // Document discoverDocumentByUserId(Integer userId);
}
