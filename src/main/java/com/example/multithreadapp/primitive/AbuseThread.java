package com.example.multithreadapp.primitive;

public class AbuseThread {



    public static void main(String[] args) {

        JoinExperiment joinExperiment = new JoinExperiment();


        Thread t1 = new Thread(() -> joinExperiment.execute());
        Thread t2 = new Thread(() -> joinExperiment.execute());
        Thread t3 = new Thread(() -> joinExperiment.execute());
        Thread t4 = new Thread(() -> joinExperiment.execute());
        Thread t5 = new Thread(() -> joinExperiment.execute());
        Thread t6 = new Thread(() -> joinExperiment.execute());


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();


        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();

        } catch (Exception e) {


            System.out.println("finished working");

        }
    }

}
