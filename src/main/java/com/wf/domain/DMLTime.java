package com.wf.domain;

import java.util.Deque;
import java.util.LinkedList;

public class DMLTime {
    private volatile Deque<Long> deque = new LinkedList<>();

    public DMLTime(){ }

    public synchronized void addElement(long time){
        deque.addLast(time);
    }

    public synchronized Deque<Long> getDeque(){
        Deque<Long> copyDeque = deque;
        deque = new LinkedList<>();
        return copyDeque;
    }
}
