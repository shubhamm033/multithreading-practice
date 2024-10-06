package com.example.multithreadapp.keyvaluestore.command;

import com.example.multithreadapp.keyvaluestore.Storage;

public class SetCommand<K, V> implements Icommand {


    Storage storage;
    K key;
    V value;


    public SetCommand(Storage storage, K key, V value) {
        this.storage = storage;
        this.key = key;
        this.value = value;
    }

    public void execute() {
        storage.update(key, value);
    }

}
