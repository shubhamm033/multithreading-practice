package com.example.multithreadapp.primitive;

public class RecursiveThread {


    public void hello() {

        System.out.println("hello");
        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName() +
                " inside hello thread"));
        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName() +
                " inside hello thread"));

        t1.start();
        t2.start();



    }


}
