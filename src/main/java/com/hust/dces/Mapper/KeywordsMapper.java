package com.hust.dces.Mapper;

import com.hust.dces.Entity.Keywords;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface KeywordsMapper {

    @Insert("insert into keywords(docid, word, wordcount) values(#{docid}, #{word}, #{wordcount})")
    Integer addKeywords(Keywords keywords);

    @Select("select * from keywords where docid = #{docid}")
    Keywords findKeywordsByDocid(Integer docid);

}
