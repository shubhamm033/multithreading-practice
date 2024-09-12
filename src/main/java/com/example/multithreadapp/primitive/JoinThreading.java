package com.example.multithreadapp.primitive;

public class JoinThreading {


    public static void main(String[] args) {

        System.out.println("Main thread working");

        JoinExperiment joinExperiment = new JoinExperiment();

        Thread t1 = new Thread(() -> joinExperiment.execute());

        t1.start();

        System.out.println(t1.getState());

        try{
            System.out.println("joining thread");
            t1.join();
        }
        catch (Exception e) {
            System.out.println(String.valueOf(e));

        }

        System.out.println(Thread.currentThread().getName() + " finishing work");

    }

}
