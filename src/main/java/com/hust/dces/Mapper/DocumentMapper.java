package com.hust.dces.Mapper;

import com.hust.dces.Entity.Document;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DocumentMapper {

    @Insert("insert into document(docname,userid,doctype,docpath,uploadtime)values(#{docname},#{userid},#{doctype},#{docpath},#{uploadtime})")
    Integer addDocument(Document document);

    @Update("update document set status =#{status} where docid=#{docid}")
    Integer updateStatusById(Integer docid,boolean status);

    @Select("select * from document where userid = #{userid}")
    List<Document> findDocumentByUserId(Integer userId);

    @Select("select * from document where docid = #{docid}")
    Document findDocumentByDocId(Integer docid);

    @Select("select * from document where docname = #{docname}")
    Document findDocumentByDocName(String docName);

    @Delete("delete from document where docid = #{docid}")
    Integer deleteDocumentByDocid(Integer docid);

    @Select("select docid from document where docpath = #{docpath}")
    Integer findDocIdByDocpath(String docpath);

}
