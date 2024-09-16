package com.example.multithreadapp.loadbalancer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ServerResource {

    List<Server> serverQueue;
    List<Server> activeServer;

    public ServerResource(){
        this.serverQueue = new ArrayList<>();
        this.activeServer = new ArrayList<>();

    }

    public void addServer(Server server) {
        this.serverQueue.add(server);
        this.activeServer.add(server);
    }

    public List<Server> getServerQueue() {
        return this.activeServer;
    }

}
