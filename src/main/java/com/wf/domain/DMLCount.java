package com.wf.domain;

public class DMLCount {
    private volatile int count = 0;

    public DMLCount(){ }

    public void addCount(){
        count ++;
    }
    public int getCount(){
        return count;
    }
}
