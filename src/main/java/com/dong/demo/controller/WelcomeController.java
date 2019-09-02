package com.dong.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(value = "/main")
public class WelcomeController {

    @RequestMapping(value = "/indexOne",method = RequestMethod.GET)
    public String index(){
        return "indexOne";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String homepage(){
        return "homepage";
    }

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }



    /** 
    * @Description: 退出
    * @Param:  
    * @return:  
    * @Author: wangxd 
    * @Date: 2019/6/22 
    */ 
    @RequestMapping(value = "/loginOut",method = RequestMethod.GET)
    public String loginOut(){
        return "login";
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "test";
    }

}
