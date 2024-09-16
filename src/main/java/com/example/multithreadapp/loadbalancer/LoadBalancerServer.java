package com.example.multithreadapp.loadbalancer;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.*;

public class LoadBalancerServer {

    ServerResource serverResource;
    private int currentServer = 0;



    public LoadBalancerServer(ServerResource serverResource) {
        this.serverResource = serverResource;
    }


    public void startServer() throws Exception {
        // Create the HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Define a context that listens to the root URL "/"
        server.createContext("/", new RootHandler(new Util(this.serverResource)));

        // Define another context for a different route, e.g., "/hello"
//        server.createContext("/hello", new HelloHandler());

        // Set an executor to handle requests (null means it will use a default executor)
        server.setExecutor(null);

        // Start the server
        server.start();
        System.out.println("Server is running on http://localhost:8080/");
    }


    class Util {

        ServerResource serverResource;
        ExecutorService executorService;




        public Util(ServerResource serverResource) {
            this.serverResource = serverResource;
            this.executorService = Executors.newFixedThreadPool(3);

        }


        public HttpResponse<String> connectServer(Server server) {
            HttpClient client = HttpClient.newHttpClient();

            // Create HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(server.getUrl()))
                    .build();

            try {
                // Send the request and receive the response
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                System.out.println("pinging server" + Thread.currentThread().getName()
                        + " "+ response);
                return response;

            }
            catch (Exception e) {
                return null;
            }

        }


         public Server nextServer() {
             if (currentServer < serverResource.activeServer.size()) {
//                 System.out.println(serverResource.activeServer.size());
                 Server server = serverResource.activeServer.get(currentServer);
                 currentServer++;
                 return server;
             }
             currentServer = 1;
             return serverResource.activeServer.get(0);

         }

    }

    class RootHandler implements HttpHandler {

        LoadBalancerServer.Util util;

        public RootHandler(LoadBalancerServer.Util util) {
            this.util = util;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
//            System.out.println("printing request");
            Callable<HttpResponse<String>> callable = () -> {
//                System.out.println("Callable Task started");
                // Simulate some computation or processing
               return util.connectServer(util.nextServer());
            };

            Future<HttpResponse<String>> future = util.executorService.submit(callable);

            try {
                // Retrieve the result from the Future (blocks until the result is available)
                HttpResponse<String> result = future.get();
                System.out.println(result);
                exchange.sendResponseHeaders(200, result.body().length());
                OutputStream os = exchange.getResponseBody();
                os.write(result.body().getBytes());
                os.close();

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();

            }

        }


}
}