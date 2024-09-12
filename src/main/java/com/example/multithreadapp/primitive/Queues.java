package com.example.multithreadapp.primitive;

import java.util.Deque;
import java.util.LinkedList;

public class Queues {

    private Deque deque;
    private int size;

    public Queues(int size) {
        this.deque = new LinkedList();
        this.size = size;
    }

    public synchronized void add(String name) {

        while(this.deque.size() == this.size) {
            try {
                System.out.println("waiting as queue is full");
                wait();
            }
            catch (Exception e) {
                System.out.println(e);
            }

        }
        System.out.println("Adding value " + name);
        this.deque.add(name);
        notify();
    }

    public synchronized void remove() {

        while(this.deque.isEmpty()) {

            try {
                System.out.println("waiting");
                wait();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("removing value " + this.deque.remove());
        notify();
    }

}
