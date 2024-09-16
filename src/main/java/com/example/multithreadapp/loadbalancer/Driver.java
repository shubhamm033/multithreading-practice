package com.example.multithreadapp.loadbalancer;

public class Driver {

    public static void main(String[] args) {

        ServerResource serverResource = new ServerResource();

        Server serverOne = new Server("http://0.0.0.0:8000/");
        Server serverTwo = new Server("http://0.0.0.0:80/");
        Server serverThree = new Server("http://0.0.0.0:9000/");

        serverResource.addServer(serverOne);
        serverResource.addServer(serverTwo);
        serverResource.addServer(serverThree);


        HealthCheck healthCheck = new HealthCheck(serverResource);

        healthCheck.execute();


        LoadBalancerServer loadBalancerServer = new LoadBalancerServer(serverResource);

        try {
            loadBalancerServer.startServer();
        }

        catch (Exception e) {

        }


    }


}
