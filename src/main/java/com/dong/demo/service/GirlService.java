package com.dong.demo.service;

import com.dong.demo.domain.Girl;
import com.github.pagehelper.Page;

public interface GirlService {

    Girl addGirl(Girl girl);

    Page<Girl> findAll();

    Girl selectSingleGirl(Girl girl);

    void selectAgeById(int id) throws Exception;
}
