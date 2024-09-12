package com.example.multithreadapp.threadpool;

import java.util.concurrent.*;

public class CustomThreadpool {

    public static void main(String[] args)  {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1,
                TimeUnit.MINUTES, new LinkedBlockingDeque<>(3), new ThreadPoolExecutor.AbortPolicy());

//        Callable<Integer> callableTask = () -> {
//            System.out.println("Callable task is executing...");
//            Thread.sleep(2000);
//            return 42;  // Return a result
//        };

        CompletableFuture<String> obj = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                System.out.println("Callable task is executing...");
                Thread.sleep(10000);

            } catch (Exception e) {
                System.out.println("j");
            }
            return "Task completed after 2 seconds";
        }, threadPoolExecutor).thenApplyAsync((String val) -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());

            } catch (Exception e) {
                System.out.println("k");
            }
            return val + "again return";
        }
        , threadPoolExecutor).thenApplyAsync((String val) -> {

            try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());

        } catch (Exception e) {
            System.out.println("k");
        }
        return val + "again return";
    });

        try {
            System.out.println(obj.get());
        }
        catch (Exception e) {

        }

    }

}
