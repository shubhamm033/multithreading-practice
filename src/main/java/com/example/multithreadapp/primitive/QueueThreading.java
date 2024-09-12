package com.example.multithreadapp.primitive;

public class QueueThreading {

    public static void main(String[] args) {


        Queues q = new Queues(5);

        Thread threadProducer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
//                    Thread.sleep(1000);
                    q.add(String.valueOf(i));
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }});

        Thread threadConsumer = new Thread(() -> {
            while(true){
                try {
                    q.remove();
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }});


        threadConsumer.start();
//        try {
//            Thread.sleep(1000);
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
        threadProducer.start();

    }

}
