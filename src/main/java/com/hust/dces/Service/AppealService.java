package com.hust.dces.Service;

import com.hust.dces.Entity.Appealdoc;

import java.util.List;

public interface AppealService {

    Integer addAppeal(Appealdoc appealdoc);

    Integer updateAppealByID(Appealdoc appealdoc);

    // Appealdoc findAppealByDocname(String docname);

    Appealdoc findAppealByID(Integer appealid);

    List<Appealdoc> findAllAppeal();

    List<Appealdoc> findAppealByUserId(Integer userId);
    // Document discoverDocumentByUserId(Integer userId);
}
