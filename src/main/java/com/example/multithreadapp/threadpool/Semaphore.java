package com.example.multithreadapp.threadpool;

public class Semaphore {

    private int count;
    private int max;

    public Semaphore(int max) {
        this.max = max;
        this.count = 0;
    }

    public synchronized  void acquire() {

        while(this.count == this.max) {
            try {
                System.out.println("Waiting to acquire count is equal to " + this.count + " " + Thread.currentThread().getName());
                wait();
            }
            catch ( Exception e) {

            }
        }
        System.out.println("Acquiring lock Count is " + this.count + " " + Thread.currentThread().getName());
        this.count++;
        notify();

    }

    public synchronized void release() {

        while(this.count == 0) {
            try {
                System.out.println("Waiting to release count is equal to " + this.count +  " " + Thread.currentThread().getName());
                wait();
            }
            catch ( Exception e) {

            }
        }
        System.out.println("Releasing lock Count is " + this.count + " " + Thread.currentThread().getName());
        this.count--;
        notify();
    }


}
