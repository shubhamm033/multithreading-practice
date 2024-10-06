package com.example.multithreadapp.threadpool;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Runner {

    // Function to make an HTTP GET request and return the response as a String
    public static String sendHttpRequest(String targetUrl) throws Exception {

        String jsonInputString = "{\"title\": \"sholay\", \"uploader_id\": \"1\"}";


        String targetURL = targetUrl; // Replace with your target URL


        // Create an instance of HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Build the HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(targetURL))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();

        try {
            // Send the request and get HttpResponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print response status code and body
//            System.out.println("Response Code: " + response.statusCode());
//            System.out.println("Response Body: " + response.body());
            return response.body() + " " + response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        // Create an ExecutorService with a thread pool of 5 threads
        // Create a ThreadPoolExecutor
        int corePoolSize = 30;
        int maxPoolSize = 35;
        long keepAliveTime = 10;  // Time that idle threads will wait for new tasks before terminating
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

        ThreadPoolExecutor executorService = new ThreadPoolExecutor(
                corePoolSize,        // core pool size
                maxPoolSize,         // maximum pool size
                keepAliveTime,       // idle thread keep-alive time
                unit,                // time unit for keep-alive
                workQueue            // work queue for tasks
        );


        // List to hold the future results of each task
        List<Future<String>> futures = new ArrayList<>();

        // Submit each task individually and handle as they complete
        for (int i = 0 ; i < 5 ; i++) {
            Thread.sleep(2000);
            Future<String> future = executorService.submit(() -> {
                    try
                    {   Thread.sleep(0);
                        return sendHttpRequest("http://13.202.213.75:8000/api/video/watch/");

                    }

                    catch (Exception e)
                    {
                        System.out.println(e);
                        return null;
                    }
                    }
                    );
            futures.add(future);
        }

        // Check the results as they complete
        for (Future<String> future : futures) {
            try {
                String response = future.get();  // This returns as soon as a task is done
                System.out.println("Response: " + response);
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error occurred: " + e.getMessage());
            }
        }

        executorService.shutdown();
    }
}


