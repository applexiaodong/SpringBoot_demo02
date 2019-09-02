package com.dong.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 定时任务
 * @author: wangxd
 * @create: 2019-06-23
 **/
@Component
public class TimeTask {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

    //定义任务每3秒执行一次
//    @Scheduled(fixedRate = 3000)
    //使用表达式，地址为 http://cron.qqe2.com/
//    @Scheduled(cron = "20-40 * * * * ?")
    public void reportCurrentTime(){
        System.out.println("当前时间-->"+ sdf.format(new Date()));
    }
}
