package com.example.multithreadapp.primitive;

public class SyncExample {





    public synchronized  void taskOne() {
        try {
            System.out.println("starting task 1");
            Thread.sleep(1000);
            System.out.println("in task 1");


        }
        catch (Exception e){

        }
    }


    public void task2() {

        System.out.println("in task 2 ");

        synchronized(this) {
            System.out.println("After synchronized in task2");
        }
    }


    public void task3() {
        System.out.println("task 3");
    }

}
