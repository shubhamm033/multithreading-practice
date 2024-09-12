package com.example.multithreadapp.threadpool;

public class ReadWriteLock {

    private boolean isWriteLock = false;
    private int readLock = 0;


    public synchronized void acquireReadLock() {
           while (isWriteLock) {
               try {
                   wait();
               }
               catch (Exception e) {

               }
           }
           readLock++;

    }


    public synchronized void releaseReadLock() {

        while (readLock == 0) {
            try {
                wait();
            }
            catch (Exception e) {

            }
        }
        readLock--;
        notify();

    }

    public synchronized void acquireWriteLock() {

        while(isWriteLock || readLock != 0) {
            try {
                wait();
            }
            catch (Exception e) {

            }
        }

        isWriteLock = true;

    }


    public synchronized void releaseWriteLock() {

        while (!isWriteLock) {
            try {
                wait();
            }
            catch (Exception e) {

            }
        }
        isWriteLock = false;
        notify();

    }


}
