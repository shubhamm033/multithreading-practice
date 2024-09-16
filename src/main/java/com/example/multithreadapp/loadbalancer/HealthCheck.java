package com.example.multithreadapp.loadbalancer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HealthCheck {

    ServerResource serverResource;

    public HealthCheck(ServerResource serverResource) {
        this.serverResource = serverResource;
    }


    private int pingServer(Server server) {
        // Create HttpClient
        HttpClient client = HttpClient.newHttpClient();
        System.out.println("pinging server Health check" + Thread.currentThread().getName());
        // Create HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(server.getUrl()))
                .build();

        try {
            // Send the request and receive the response
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response);
            return response.statusCode();

        }
       catch (Exception e) {
            return 0;
       }

    }

    public void pinging() {
        for (Server server : serverResource.serverQueue) {
            if (pingServer(server) == 0) {
                serverResource.activeServer.remove(server);
            }
            else {
                if (!serverResource.getServerQueue().contains(server)) {
                serverResource.activeServer.add(server);
                }
            }
        }
    }

    public void execute() {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        scheduler.scheduleAtFixedRate(() -> pinging(), 1, 30, TimeUnit.SECONDS);

        // Main thread sleeps to allow the daemon task to run for a while
        try {
            Thread.sleep(10000); // Sleep for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


}
}
