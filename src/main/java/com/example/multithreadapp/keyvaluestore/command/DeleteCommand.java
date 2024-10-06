package com.example.multithreadapp.keyvaluestore.command;

import com.example.multithreadapp.keyvaluestore.Storage;

public class DeleteCommand implements Icommand {

    Storage storage;
    String key;


    public DeleteCommand(Storage storage, String key) {
        this.storage = storage;
        this.key = key;
    }

    @Override
    public void execute() {
        storage.delete(key);
    }
}
