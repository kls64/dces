package com.hust.dces.ServiceImpl;

import com.hust.dces.Entity.Appealdoc;
import com.hust.dces.Mapper.AppealMapper;
import com.hust.dces.Service.AppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppealServiceImpl implements AppealService {

    @Autowired
    private AppealMapper appealMapper;

    @Override
    public Integer addAppeal(Appealdoc appealdoc) {
        return appealMapper.addAppeal(appealdoc);
    }

    // @Override
    // public Document discoverDocumentByUserId(Integer userId) {
    //    return appealMapper.discoverDocumentByUserId(userId);
    // }
}
