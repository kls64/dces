package com.hust.dces.Service;

import com.github.pagehelper.PageInfo;
import com.hust.dces.Entity.SensitiveWord;
import com.hust.dces.Entity.SensitiveWordsType;

import java.util.List;

public interface SensitiveWordService {
    PageInfo<SensitiveWord>findAllSensitiveWords(Integer pageIndex,Integer pageSize);

    PageInfo<SensitiveWord> findWordByTypeIDAndWord(Integer pageIndex,Integer pageSize,Integer wordtypeid,String sensitiveword);

    PageInfo<SensitiveWord>findWordByWord(Integer pageIndex,Integer pageSize,String sensitiveword);

    int deleteGuanggao(Integer itemid);

    Integer addWord(String sensitiveword,int wordtypeid);

    List<SensitiveWordsType> findAllType();

    List<String>findAllSensitiveWordsName();

}
