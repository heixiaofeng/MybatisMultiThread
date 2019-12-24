package com.wf.domain;

public class DMLCount {
    private int count;
    private boolean flag;

    public DMLCount(int count, boolean flag){
        this.count = count;
        this.flag = flag;
    }

    public synchronized void setFlag(boolean flag){
        this.flag = flag;
    }
    public synchronized boolean getFlag(){
        return flag;
    }
    public synchronized void setCount(){
        count ++;
    }
    public synchronized int getCount(){
        return count;
    }
}
