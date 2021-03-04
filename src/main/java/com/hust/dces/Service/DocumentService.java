package com.hust.dces.Service;

import com.hust.dces.Entity.Document;

import java.util.List;

public interface DocumentService {

    Integer addDoc(Document document);

    List<Document> findDocumentByUserId(Integer userId);

    Document findDocumentByDocId(Integer docid);

    Integer deleteDocumentByDocid(Integer docid);

    Document findDocumentByDocName(String docName);

    Integer findDocIdByDocpath(String docpath);

}
