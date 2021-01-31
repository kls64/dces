package com.hust.dces.ServiceImpl;

import com.hust.dces.Entity.SensitiveWord;
import com.hust.dces.Mapper.SensitiveWordMapper;
import com.hust.dces.Service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {
    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    public List<SensitiveWord> getGuanggaoWords(){
        return sensitiveWordMapper.getGuanggaoWords();
    }

    @Override
    public int deleteGuanggao(Integer itemid) {
        return sensitiveWordMapper.deleteGuanggao(itemid);
    }
}
