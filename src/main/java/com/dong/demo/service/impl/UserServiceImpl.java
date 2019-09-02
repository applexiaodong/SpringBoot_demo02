package com.dong.demo.service.impl;

import com.dong.demo.dao.UserCustomMapper;
import com.dong.demo.dao.UserMapper;
import com.dong.demo.domain.User;
import com.dong.demo.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserCustomMapper userCustomMapper;

    @Override
    public User selectSingleUser(User user){
        return userMapper.selectOne(user);
    }

    @Override
    public Page<User> findAll(User user) {
        return userCustomMapper.selectAllByPage(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userCustomMapper.deleteUserById(id);
    }
}
