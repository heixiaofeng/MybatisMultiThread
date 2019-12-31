package com.wf.service;

import com.wf.domain.DMLCount;
import com.wf.domain.DMLTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args){
        int threadCount = Integer.parseInt(args[0]);
        int operationCount = Integer.parseInt(args[1]);
        DMLCount dmlCount = new DMLCount();
        DMLTime dmlTime = new DMLTime();
        //工作线程
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        //统计线程
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        //启动日志线程
        LogThreadService logThreadService = new LogThreadService(dmlTime);
        scheduledExecutorService.scheduleAtFixedRate(logThreadService, 0, 1, TimeUnit.SECONDS);
        //启动统计线程
        CountThreadService countThreadService = new CountThreadService(dmlCount);
        scheduledExecutorService.scheduleAtFixedRate(countThreadService, 0, 1, TimeUnit.SECONDS);
        //启动工作线程,同一个工作线程对象启动多次
        WorkerThreadService workerThreadService = new WorkerThreadService(dmlCount, dmlTime, operationCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(workerThreadService);
        }
    }
}
