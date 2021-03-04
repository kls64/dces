package com.hust.dces.ServiceImpl;

import com.hust.dces.Entity.Keywords;
import com.hust.dces.Mapper.KeywordsMapper;
import com.hust.dces.Service.KeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordsServiceImpl implements KeywordsService {

    @Autowired
    private KeywordsMapper keywordsMapper;

    @Override
    public Integer addKeywords(Keywords keywords) {
        return keywordsMapper.addKeywords(keywords);
    }

    @Override
    public Keywords findKeywordsByDocid(Integer docid) {
        return keywordsMapper.findKeywordsByDocid(docid);
    }
}
