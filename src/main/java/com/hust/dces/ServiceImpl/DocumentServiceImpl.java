package com.hust.dces.ServiceImpl;

import com.hust.dces.Entity.Document;
import com.hust.dces.Mapper.DocumentMapper;
import com.hust.dces.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public Integer addDoc(Document document) {
        return documentMapper.addDocument(document);
    }

    @Override
    public List<Document> findDocumentByUserId(Integer userId) {
        return documentMapper.findDocumentByUserId(userId);
    }

}
