package com.wf.service;

import com.wf.domain.DMLTime;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Deque;

public class LogThreadService implements Runnable{

    private DMLTime dmlTime;

    public LogThreadService(DMLTime dmlTime){
        this.dmlTime = dmlTime;
    }

    @Override
    public void run() {
        Deque<Double> deque = dmlTime.getDeque();
        int size = deque.size();
        System.out.println("日志线程总数量:" + size);
        if (size > 0) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
