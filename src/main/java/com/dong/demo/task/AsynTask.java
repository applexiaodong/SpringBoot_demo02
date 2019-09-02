package com.dong.demo.task;

import com.dong.demo.domain.AsyncResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @description 异步调用执行
 * @author: wangxd
 * @create: 2019-06-23
 **/
@Component
public class AsynTask {

    @Async
    public Future<Boolean> doTask11() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务1耗时："+(end-start)+"毫秒");
        return  new AsyncResult(true);
    }

    @Async
    public Future<Boolean> doTask22() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(800);
        long end = System.currentTimeMillis();
        System.out.println("任务2耗时："+(end-start)+"毫秒");
        return  new AsyncResult(true);
    }

    @Async
    public Future<Boolean> doTask33() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(600);
        long end = System.currentTimeMillis();
        System.out.println("任务3耗时："+(end-start)+"毫秒");
        return  new AsyncResult(true);

    }

}
