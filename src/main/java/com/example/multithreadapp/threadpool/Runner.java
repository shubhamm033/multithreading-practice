package com.example.multithreadapp.threadpool;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Runner {

    // Function to make an HTTP GET request and return the response as a String
    public static String sendHttpRequest(String targetUrl) throws Exception {
        HttpURLConnection connection = null;

        try {
            // Create a connection to the URL
            URL url = new URL(targetUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // Get the response code
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed with HTTP code: " + responseCode);
            }

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Return the response as a String
            return response.toString();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static void main(String[] args) {
        // Create an ExecutorService with a thread pool of 5 threads
        // Create a ThreadPoolExecutor
        int corePoolSize = 5;
        int maxPoolSize = 10;
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
        for (int i = 0 ; i < 500 ; i++) {
            Future<String> future = executorService.submit(() -> {
                    try
                    {   Thread.sleep(10);
                        return sendHttpRequest("http://127.0.0.1:8000/limited");

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
