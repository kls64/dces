package com.hust.dces.Service;

import com.hust.dces.Entity.Keywords;

public interface KeywordsService {

    Integer addKeywords(Keywords keywords);

    Keywords findKeywordsByDocid(Integer docid);

}
