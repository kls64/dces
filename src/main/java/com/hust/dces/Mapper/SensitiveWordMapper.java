package com.hust.dces.Mapper;

import com.hust.dces.Entity.SensitiveWord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SensitiveWordMapper {

    @Select("select * from sensitivewordslib where wordtypeid = 1")
    List<SensitiveWord> getGuanggaoWords();

    @Delete("delete from sensitivewordslib where itemid = #{itemid}")
    int deleteGuanggao(Integer itemid);
}
