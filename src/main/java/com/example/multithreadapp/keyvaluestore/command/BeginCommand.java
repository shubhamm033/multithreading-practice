package com.example.multithreadapp.keyvaluestore.command;

import com.example.multithreadapp.keyvaluestore.Storage;

public class BeginCommand implements Icommand {

    Storage storage;

    public BeginCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.begin();
    }
}
