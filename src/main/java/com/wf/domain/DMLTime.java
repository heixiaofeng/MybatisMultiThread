package com.wf.domain;

import java.util.Deque;
import java.util.LinkedList;

public class DMLTime {
    private volatile Deque<Double> deque = new LinkedList<>();
    private volatile Deque<Double> copyDeque = new LinkedList<>();

    public DMLTime(){ }

    public synchronized void addElement(double time) {
        deque.addLast(time);
    }

    public synchronized Deque<Double> getDeque() {
        Deque<Double> tempDeque;
        tempDeque = deque;
        deque = copyDeque;
        copyDeque = tempDeque;
        deque.clear();
        return copyDeque;
    }
}
