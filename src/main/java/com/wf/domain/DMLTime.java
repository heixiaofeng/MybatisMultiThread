package com.wf.domain;

import java.util.Deque;
import java.util.LinkedList;

public class DMLTime {
    private volatile Deque<Double> deque = new LinkedList<>();

    public DMLTime(){ }

    public  void addElement(double time){
        deque.addLast(time);
    }

    public  Deque<Double> getDeque(){
        Deque<Double> copyDeque = deque;
        deque = new LinkedList<>();
        return copyDeque;
    }
}
