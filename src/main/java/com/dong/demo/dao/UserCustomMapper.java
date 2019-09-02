package com.dong.demo.dao;

import com.dong.demo.domain.User;
import com.github.pagehelper.Page;

public interface UserCustomMapper {
    //分页查询
    Page<User> selectAllByPage(User user);

    //逻辑删除用户
    int deleteUserById(Integer id);
}