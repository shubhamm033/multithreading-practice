package com.example.multithreadapp.keyvaluestore.command;

import com.example.multithreadapp.keyvaluestore.Storage;

public class RollbackCommand implements Icommand {

    Storage storage;

    public RollbackCommand(Storage storage) {
        this.storage = storage;
    }


    @Override
    public void execute() {
        storage.rollback();
    }

}
