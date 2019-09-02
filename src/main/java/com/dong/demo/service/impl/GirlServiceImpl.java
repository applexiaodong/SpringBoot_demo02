package com.dong.demo.service.impl;

import com.dong.demo.Exception.GirlException;
import com.dong.demo.dao.GirlCustomMapper;
import com.dong.demo.dao.GirlMapper;
import com.dong.demo.domain.Girl;
import com.dong.demo.enums.ResultEnum;
import com.dong.demo.service.GirlService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "girlService")
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlMapper girlMapper;

    @Autowired
    private GirlCustomMapper girlCustomMapper;

    @Override
    public Girl addGirl(Girl girl) {
        int result = girlMapper.insert(girl);
        if (result<=0){
            return null;
        }
        return girlMapper.selectOne(girl);
    }

    @Override
    public Page<Girl> findAll() {
        return girlCustomMapper.selectAllByPage();
    }

    @Override
    public Girl selectSingleGirl(Girl girl){
        return girlMapper.selectOne(girl);
    }

    @Override
    public void selectAgeById(int id) throws Exception {
        Girl girl = girlMapper.selectByPrimaryKey(id);
        Integer age = girl.getAge();
        if (age<=10){
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age>10 && age<16){
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }else{
            throw new GirlException(ResultEnum.COLLAGE_SCHOOL);
        }
    }

}
