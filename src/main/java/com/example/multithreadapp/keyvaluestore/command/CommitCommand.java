package com.example.multithreadapp.keyvaluestore.command;

import com.example.multithreadapp.keyvaluestore.Storage;

public class CommitCommand implements Icommand  {

    Storage storage;

    public CommitCommand(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        storage.commit();
    }
}
