package com.example.multithreadapp.primitive;

public class Threading extends Thread {

    public static void main(String[] args) {

        Processing processing = new Processing();
        Thread t1 = new Thread(processing);

        Thread t2 = new Thread(processing);
        t2.setDaemon(true);
        Thread t3 = new Thread(processing);
        t3.setDaemon(true);
        Thread t4 = new Thread(processing);
        t4.setDaemon(true);
        Thread t5 = new Thread(processing);
        t5.setDaemon(true);
        Thread t6 = new Thread(processing);
        Thread t7 = new Thread(processing);
        Thread t8 = new Thread(processing);
        Thread t9 = new Thread(processing);
        Thread t10 = new Thread(processing);
        Thread t11= new Thread(processing);



        t1.start();
        System.out.println(t1.getName() + " " + t1.getState());
        t2.start();
        System.out.println(t2.getName() + " " + t2.getState());
        t3.start();
        try {
            t3.sleep(100);

        }
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(t3.getName() + " " + t3.getState());
        t4.start();
        System.out.println(t4.getName() + " " + t4.getState());
        t5.start();
        System.out.println(t5.getName() + " " + t5.getState());
        t6.start();
        System.out.println(t6.getName() + " " + t6.getState());
        t7.start();
        System.out.println(t6.getName() + " " + t6.getState());

        t8.start();
        System.out.println(t6.getName() + " " + t6.getState());

        t9.start();
        System.out.println(t6.getName() + " " + t6.getState());

        t10.start();
        System.out.println(t6.getName() + " " + t6.getState());





        System.out.println(t1.getName() + " " + t1.getState());
        System.out.println(t2.getName() + " " + t2.getState());
        System.out.println(t3.getName() + " " + t3.getState());
        System.out.println(t4.getName() + " " + t4.getState());
        System.out.println(t5.getName() + " " + t5.getState());
        System.out.println(t6.getName() + " " + t6.getState());
        System.out.println(t7.getName() + " " + t7.getState());
        System.out.println(t8.getName() + " " + t8.getState());
        System.out.println(t9.getName() + " " + t9.getState());
        System.out.println(t10.getName() + " " + t10.getState());



    }





}
