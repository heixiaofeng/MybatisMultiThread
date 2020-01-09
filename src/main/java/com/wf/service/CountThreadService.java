package com.wf.service;

import com.wf.domain.DMLCount;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CountThreadService implements Runnable{

    private DMLCount dmlCount;
    private int oldCount = 0;

    public CountThreadService(DMLCount dmlCount){
        this.dmlCount = dmlCount;
    }

    @Override
    public void run() {
        int newCount = dmlCount.getCount();
        System.out.println("统计线程结果总数量：" + newCount);
        int data = newCount - oldCount;
        if (data > 0) {
            File file = new File("src/main/resources/file/countThread.txt");
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(String.valueOf(data).getBytes());
                fileOutputStream.write(" ".getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        oldCount = newCount;
    }
}
