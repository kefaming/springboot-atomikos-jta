package com.atomikos.jta.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
public class TestSchedule {
    Logger logger = LoggerFactory.getLogger(TestSchedule.class);
 
    @Async
//    @Scheduled(fixedDelay = 1500)  //定时任务1
    public void printXXXXXXX(){
        try{
            Thread.sleep(1500);  //睡眠5秒
            logger.error(Thread.currentThread().getName()); //打印当前线程名字
 
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
 
    @Async
//    @Scheduled(fixedDelay = 1500)  //定时任务2
    public void printYYYYYYY(){
        try{
            Thread.sleep(1500);
            logger.error(Thread.currentThread().getName());
 
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
