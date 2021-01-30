package com.hust.dces.Mapper;

import com.hust.dces.Entity.Document;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DocumentMapper {
    @Insert("insert into document(docname,userid,doctype,docpath,uploadtime)values(#{docname},#{userid},#{doctype},#{docpath},#{uploadtime})")
    Integer addDocument(Document document);

    @Select("select * from document where userid =#{userid}")
    List<Document> findDocumentByUserId( Integer userId);

}
