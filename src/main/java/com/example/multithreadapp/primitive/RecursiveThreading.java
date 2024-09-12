package com.example.multithreadapp.primitive;

public class RecursiveThreading {

    public static void main(String[] args) {

        RecursiveThread recursiveThread = new RecursiveThread();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            recursiveThread.hello();
        });
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            recursiveThread.hello();
        });
        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            recursiveThread.hello();
        });

        t1.start();
        t2.start();
        t3.start();

    }

}
