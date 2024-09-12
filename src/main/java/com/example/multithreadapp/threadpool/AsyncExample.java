package com.example.multithreadapp.threadpool;
import java.util.concurrent.CompletableFuture;

public class AsyncExample {

        public static void main(String[] args) throws Exception {
            // Asynchronous task equivalent to async def in Python
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000); // Simulating async task
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Task completed after 2 seconds";
            });

            // Non-blocking: Attach a callback (similar to Python's await)
            future.thenAccept(result -> System.out.println(result));

            System.out.println("Doing other work while the task is running...");

            // Wait for the task to complete before exiting the program (for demo purposes)
            future.get();
        }
    }

