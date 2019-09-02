package com.dong.demo.controller;

import com.dong.demo.domain.ResultMessage;
import com.dong.demo.domain.User;
import com.dong.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户一览
    @RequestMapping("/userView")
    public String showUser(){
        return "user/userList";
    }

    //用户添加
    @RequestMapping("/addView")
    public String addUser(){
        return "user/userAdd";
    }

    @RequestMapping(value = "/add",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultMessage addUser(@RequestBody User user){
        ResultMessage result = new ResultMessage();
        user.setCreateTime(new Date());
        user.setDeleteFlag("0");
        int number = userService.addUser(user);
        String msg = "fail";
        if (number > 0){
            msg = "success";
        }
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping(value = "/update",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultMessage updateUser(@RequestBody User user){
        ResultMessage result = new ResultMessage();
        user.setUpdateTime(new Date());
        int number = userService.updateUser(user);
        String msg = "fail";
        if (number > 0){
            msg = "success";
        }
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping(value = "/delete/{id}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultMessage deleteGirl(@PathVariable Integer id){
        ResultMessage result = new ResultMessage();

        int number = userService.deleteUser(id);
        String msg = "error";
        if (number > 0){
            msg = "success";
        }
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    @RequestMapping(value = "/userList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResultMessage findAll(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "2") int pageSize,
                                 @RequestParam(required = false) String username,@RequestParam(required = false) String state){
        ResultMessage result = new ResultMessage();
        PageHelper.startPage(pageNo,pageSize);
        User user = new User();
        if (username != null) {
            user.setUsername(username);
        }
        if (state != null) {
            user.setState(state);
        }
        PageInfo<User> pageInfo = new PageInfo<>(userService.findAll(user));
        result.setCode(0);
        result.setMsg("OK");
        result.setCount(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;

    }
}
