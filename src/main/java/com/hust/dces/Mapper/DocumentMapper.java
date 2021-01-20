package com.hust.dces.Mapper;

import com.hust.dces.Entity.Document;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DocumentMapper {
    @Insert("insert into document(docname,userid,doctype,docpath,uploadtime)values(#{docname},#{userid},#{doctype},#{docpath},#{uploadtime})")
    Integer addDocument(Document document);
}
