package com.hust.dces.ServiceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hust.dces.Entity.SensitiveWord;
import com.hust.dces.Entity.SensitiveWordsType;
import com.hust.dces.Mapper.SensitiveWordMapper;
import com.hust.dces.Service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {
    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    public PageInfo<SensitiveWord> findAllSensitiveWords(Integer pageIndex,Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        List<SensitiveWord> lists = sensitiveWordMapper.findAllSensitiveWords();
        PageInfo<SensitiveWord>info = new PageInfo<SensitiveWord>(lists);
        return info;
    }

    @Override
    public PageInfo<SensitiveWord> findWordByTypeIDAndWord(Integer pageIndex, Integer pageSize, Integer wordtypeid, String sensitiveword) {
        PageHelper.startPage(pageIndex,pageSize);
        List<SensitiveWord> lists = sensitiveWordMapper.findWordByTypeIDAndWord(wordtypeid,sensitiveword);
        PageInfo<SensitiveWord>info = new PageInfo<SensitiveWord>(lists);
        return info;
    }

    @Override
    public PageInfo<SensitiveWord> findWordByWord(Integer pageIndex, Integer pageSize, String sensitiveword) {
        PageHelper.startPage(pageIndex,pageSize);
        List<SensitiveWord> lists = sensitiveWordMapper.findWordByWord(sensitiveword);
        PageInfo<SensitiveWord>info = new PageInfo<SensitiveWord>(lists);
        return info;
    }

    @Override
    public int deleteGuanggao(Integer itemid) {
        return sensitiveWordMapper.deleteGuanggao(itemid);
    }

    @Override
    public Integer addWord(String sensitiveword, int wordtypeid) {
        return sensitiveWordMapper.addWord(sensitiveword,wordtypeid);
    }


    public List<SensitiveWordsType>findAllType(){
        return sensitiveWordMapper.findAllType();
    }

    @Override
    public List<String> findAllSensitiveWordsName() {
        return sensitiveWordMapper.findAllSensitiveWordsName();
    }

}
