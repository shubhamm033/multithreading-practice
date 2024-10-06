package com.example.multithreadapp.keyvaluestore.command;

import com.example.multithreadapp.keyvaluestore.Storage;

public class GetCommand<K> implements Icommand {

    Storage storage;

    K key;

    public GetCommand(Storage storage, K key) {
        this.storage = storage;
        this.key = key;

    }


    @Override
    public void execute() {
        System.out.println("returened value from database is :"  + this.key + " " +
                storage.get(key));
    }
}
