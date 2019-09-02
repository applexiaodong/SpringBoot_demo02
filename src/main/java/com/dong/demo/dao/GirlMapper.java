package com.dong.demo.dao;

import com.dong.demo.domain.Girl;
import com.dong.demo.mapper.MyMapper;
import com.github.pagehelper.Page;

public interface GirlMapper extends MyMapper<Girl> {
    Page<Girl> selectAllByPage();
}