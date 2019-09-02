package com.dong.demo.service;

import com.dong.demo.domain.User;
import com.github.pagehelper.Page;

public interface UserService {

    User selectSingleUser(User user);

    Page<User> findAll(User user);

    //添加用户
    int addUser(User user);

    //修改用户
    int updateUser(User user);

    //逻辑删除用户
    int deleteUser(Integer id);
}
