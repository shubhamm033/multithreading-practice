package com.example.multithreadapp.threadpool;

public class CallbackThreading {

    public static void main(String[] args) {

        SchduledCallbacks schduledCallbacks = new SchduledCallbacks();

        Thread threadStart = new Thread(() -> schduledCallbacks.start());

        Thread thread = new Thread(() -> schduledCallbacks.registerCallback(new
                SchduledCallbacks.Callback(1, "callback1")));


        Thread threadTwo = new Thread(() -> schduledCallbacks.registerCallback(new
                SchduledCallbacks.Callback(2, "callback2")));

        threadStart.start();

        try {
            Thread.sleep(100);
        }
        catch (Exception e) {

        }

        threadTwo.start();
        thread.start();

    }

}
