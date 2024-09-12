package com.example.multithreadapp.primitive;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;



public class Threadpool {

    public static void main(String[] args) {
            // Create a ThreadPoolExecutor with 2 core threads, 4 max threads, and a 10-task queue
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
            JoinExperiment joinExperiment = new JoinExperiment();


            for (int i = 1; i <= 10; i++) {
                executor.execute(() -> joinExperiment.execute());  // Execute the task
            }

            executor.shutdown();  // Shut down the executor after tasks are done
        }
    }

