package com.hust.dces.ServiceImpl;

import com.hust.dces.Entity.Appealdoc;
import com.hust.dces.Mapper.AppealMapper;
import com.hust.dces.Service.AppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppealServiceImpl implements AppealService {

    @Autowired
    private AppealMapper appealMapper;

    @Override
    public Integer addAppeal(Appealdoc appealdoc) {
        return appealMapper.addAppeal(appealdoc);
    }

    @Override
    public Integer updateAppealByID(Appealdoc appealdoc) {
        return appealMapper.updateAppealByID(appealdoc);
    }

    @Override
    public Appealdoc findAppealByID(Integer appealid) {
        return appealMapper.findAppealByID(appealid);
    }

    /*
    // @Override
    // public Appealdoc findAppealByDocname(String docname) {
        return appealMapper.findAppealByDocname(docname);
    }
    */


    @Override
    public List<Appealdoc> findAllAppeal() {
        return appealMapper.findAllAppeal();
    }

    @Override
    public List<Appealdoc> findAppealByUserId(Integer userId) {
        return appealMapper.findAppealByUserId(userId);
    }

    // @Override
    // public Document discoverDocumentByUserId(Integer userId) {
    //    return appealMapper.discoverDocumentByUserId(userId);
    // }
}
