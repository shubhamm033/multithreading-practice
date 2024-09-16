package com.example.multithreadapp.loadbalancer;

public class Server {

    private final String url;
    private boolean status;

    public Server(String url) {
        this.url = url;
        this.status = true;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUrl() {
        return this.url;
    }
}
