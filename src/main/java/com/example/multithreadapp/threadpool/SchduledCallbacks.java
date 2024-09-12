package com.example.multithreadapp.threadpool;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SchduledCallbacks {

    PriorityQueue<Callback> queue;

    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public SchduledCallbacks() {

        this.queue = new PriorityQueue(new Comparator<Callback>() {
            public int compare(Callback o1, Callback o2) {
                return (int) (o1.executeTime - o2.executeTime);
            }
        });
    }

    private long findNextTime() {
        long currentTime = System.currentTimeMillis();
        long diff = queue.peek().executeTime - currentTime;
        System.out.println("differnece between time is " + diff);
        return diff;

    }

    public void registerCallback(Callback c) {
        lock.lock();
        this.queue.add(c);

        System.out.println("Queue size is " + this.queue.size() +  " " + Thread.currentThread().getName());

        condition.signal();
        lock.unlock();
    }


    public void start() {
        System.out.println("Running thread " + Thread.currentThread().getName());

        long nexttime = 0;

        while (true) {
            lock.lock();
            System.out.println("Lock taken " + Thread.currentThread().getName());
            if (this.queue.isEmpty()) {

                try {
                    condition.await();
                } catch (Exception e) {

                }

            }

            while (!this.queue.isEmpty()) {

                nexttime = findNextTime();


                if (nexttime <= 0) {
                    break;

                }

                try {
                    condition.await(nexttime, TimeUnit.MILLISECONDS);
                } catch (Exception e) {

                }
            }

            Callback callback = queue.poll();
            System.out.println("Printing callback");
            callback.execute();

            lock.unlock();

        }

    }

    static class Callback {

        private long executeTime;
        private String message;

        public Callback(long executeAfter, String message) {
            this.executeTime = System.currentTimeMillis() + 1000*executeAfter;
            this.message = message;
        }

        public void execute() {
            System.out.println(this.message);
        }

    }



}
