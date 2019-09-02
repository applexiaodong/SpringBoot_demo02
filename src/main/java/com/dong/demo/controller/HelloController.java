package com.dong.demo.controller;


import com.dong.demo.domain.Boy;
import com.dong.demo.task.AsynTask;
import com.dong.demo.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Future;

@RestController
public class HelloController {

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private Boy boy;

    @Value("${jwt.key}")
    private String key;

    @Autowired
    private AsynTask asynTask;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public String hello(@PathVariable(name = "id",required = true) Integer id){

        return "亲爱的"+boy.getName()+"，今年"+boy.getAge()+"岁，性别:"+boy.getSex()+"，编号："+id+"，key:"+key;
    }

    @RequestMapping(value = "/look",method = RequestMethod.GET)
    public String say(@RequestParam(name = "id",required = false,defaultValue = "100") Integer id){

        return "亲爱的"+boy.getName()+"，今年"+boy.getAge()+"岁，性别:"+boy.getSex()+",编号："+id.toString();
    }

    @RequestMapping("/testAsyn")
    public String testAsync() throws Exception{
        long start = System.currentTimeMillis();
        Future<Boolean> a = asynTask.doTask11();
        Future<Boolean> b = asynTask.doTask22();
        Future<Boolean> c = asynTask.doTask33();

        while(!a.isDone()||!b.isDone()||!c.isDone()){
            if (a.isDone() && b.isDone() && c.isDone()){
                break;
            }
        }
        long end = System.currentTimeMillis();
        return "任务全部完成，总耗时："+(end-start)+"毫秒";
    }

    @GetMapping("/redis")
    public String getRedis(){
        redisUtil.set("title","今天天气很热！！！",1);
        long expireTime = redisUtil.expire("title",600,1);
        logger.info("生效时间"+expireTime);
        String expireString = redisUtil.get("title",1);
        return expireString;
    }

}
