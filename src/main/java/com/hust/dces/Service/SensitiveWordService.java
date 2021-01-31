package com.hust.dces.Service;

import com.hust.dces.Entity.SensitiveWord;

import java.util.List;

public interface SensitiveWordService {
    List<SensitiveWord> getGuanggaoWords();

    int deleteGuanggao(Integer itemid);
}
