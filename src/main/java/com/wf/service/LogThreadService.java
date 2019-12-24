package com.wf.service;

import com.wf.domain.DMLTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Deque;

public class LogThreadService implements Runnable{

    private DMLTime dmlTime;

    public LogThreadService(DMLTime dmlTime){
        this.dmlTime = dmlTime;
    }

    @Override
    public void run() {
        Deque<Long> deque = dmlTime.getDeque();
        System.out.println("deque isEmpty:"+deque.isEmpty());
        File file = new File("src/main/resources/file/logThread.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((deque.size()+":").getBytes());
            for (long time: deque) {
//                System.out.println("日志文件单次操作时间："+time);
                fileOutputStream.write(String.valueOf(time).getBytes());
                fileOutputStream.write(" ".getBytes());
            }
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
