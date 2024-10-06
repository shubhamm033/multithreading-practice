package com.example.multithreadapp.keyvaluestore.command;

import com.example.multithreadapp.keyvaluestore.Storage;

public class AddCommand<K, V> implements Icommand {

    Storage storage;
    K key;
    V value;

    public AddCommand(Storage storage, K key, V value) {
        this.storage = storage;
        this.key = key;
        this.value = value;

    }

    @Override
    public void execute() {
        storage.add(key, value);
    }



}
