package com.hust.dces.Mapper;

import com.hust.dces.Entity.SensitiveWord;
import com.hust.dces.Entity.SensitiveWordsType;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SensitiveWordMapper {

    @Select("select * from sensitivewordslib,sensitivewordstype where sensitivewordslib.wordtypeid =sensitivewordstype.wordtypeid")
    List<SensitiveWord> findAllSensitiveWords();

    @Delete("delete from sensitivewordslib where itemid = #{itemid}")
    int deleteGuanggao(Integer itemid);

    @Insert("insert into sensitivewordslib (sensitiveword,wordtypeid) values(#{sensitiveword},#{wordtypeid})")
    Integer addWord(String sensitiveword,Integer wordtypeid);

    @Select("select lib.*,type.wordtype from sensitivewordslib lib join sensitivewordstype type on type.wordtypeid=lib.wordtypeid where type.wordtypeid=#{wordtypeid} and lib.sensitiveword like '%${sensitiveword}%'")
    List<SensitiveWord>findWordByTypeIDAndWord(@Param("wordtypeid")Integer wordtypeid,@Param("sensitiveword")String sensitiveword);

    @Select("select lib.*,type.wordtype from sensitivewordslib lib join sensitivewordstype type on type.wordtypeid=lib.wordtypeid where lib.sensitiveword like '%${sensitiveword}%'")
    List<SensitiveWord>findWordByWord(@Param("sensitiveword")String sensitiveword);

    @Select("select * from sensitivewordstype")
    List<SensitiveWordsType>findAllType();

}
