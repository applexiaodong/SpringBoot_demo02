package com.dong.demo.dao;

import com.dong.demo.domain.Girl;
import com.github.pagehelper.Page;

public interface GirlCustomMapper {
    Page<Girl> selectAllByPage();
}