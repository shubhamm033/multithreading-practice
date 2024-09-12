package com.example.multithreadapp.primitive;

import java.math.BigInteger;

public class JoinExperiment {

    public synchronized void execute() {

        try {
            System.out.println("Ruuning join experiment");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getThreadGroup());
            System.out.println("Finshed join experiment");
        }
         catch (Exception e) {
        }
    }


}
