package com.wf.service;

import com.wf.domain.DMLTime;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Deque;
import java.util.concurrent.*;

public class LogThreadService implements Runnable{

    private DMLTime dmlTime;

    public LogThreadService(DMLTime dmlTime){
        this.dmlTime = dmlTime;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Boolean> future = executorService.submit(new Caller(dmlTime));
        try{
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("超时");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            executorService.shutdownNow();
        }
    }
}

/**
 * 为了解决线程假死
 */
class Caller implements Callable<Boolean>{
    private DMLTime dmlTime;

    public Caller(DMLTime dmlTime){
        this.dmlTime = dmlTime;
    }

    @Override
    public Boolean call() throws Exception {
        Deque<Double> deque = dmlTime.getDeque();
        int size = deque.size();
        System.out.println("日志线程总数量:"+ size);
        File file = new File("src/main/resources/file/logThread.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((size + ":").getBytes());
            for (double time : deque) {
                fileOutputStream.write(String.valueOf(time).getBytes());
                fileOutputStream.write(" ".getBytes());
            }
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
