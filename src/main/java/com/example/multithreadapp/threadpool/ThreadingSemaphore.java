package com.example.multithreadapp.threadpool;

public class ThreadingSemaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);

        Thread t1 = new Thread(() -> {

            for (int i =0 ; i < 10 ; i++) {

                try {
                    Thread.sleep(100);
                    semaphore.acquire();

                } catch (Exception e) {

                }
            }
        });

        Thread t2 = new Thread(() -> {

            for (int i =0 ; i < 10 ; i++) {
                try {

                    Thread.sleep(500);
                    semaphore.release();
                }
                catch (Exception e) {

                }
            }
        });


        t1.start();
        t2.start();

    }


}
