package com.example.multithreadapp.primitive;

public class SyncExampleThreading {

    public static void main(String[] args) {

        SyncExample syncExample = new SyncExample();

        Thread t1 = new Thread(() -> syncExample.taskOne());
        Thread t2 = new Thread(() -> syncExample.task2());
        Thread t3 = new Thread(() -> syncExample.task3());


        t1.start();
        t2.start();
        t3.start();

    }

}
