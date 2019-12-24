package com.wf.service;

import com.wf.domain.DMLCount;
import com.wf.domain.DMLTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args){
        DMLCount dmlCount = new DMLCount(0, true);
        DMLTime dmlTime = new DMLTime();
        //工作线程
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        //统计线程
        ScheduledExecutorService countScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //日志线程
        ScheduledExecutorService logScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //启动统计线程
        countScheduledExecutorService.scheduleAtFixedRate(new CountThreadService(dmlCount), 0, 1, TimeUnit.SECONDS);
        //启动日志线程
        logScheduledExecutorService.scheduleAtFixedRate(new LogThreadService(dmlTime), 0, 1, TimeUnit.SECONDS);
        //启动工作线程
        for(int i = 0; i < 50; i ++){
            executorService.submit(new WorkerThreadService(dmlCount, dmlTime));
        }

        //关闭线程
        executorService.shutdown();
        //scheduledExecutorService.shutdown();
    }
}
