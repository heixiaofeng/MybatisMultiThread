package com.wf.domain;

public class DMLCount {
    private volatile int count = 0;

    public DMLCount() {
    }

    public synchronized void addCount() {
        count++;
    }

    public synchronized void cutCount() {
        count--;
    }

    public synchronized int getCount() {
        return count;
    }
}
