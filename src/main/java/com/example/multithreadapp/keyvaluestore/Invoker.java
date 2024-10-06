package com.example.multithreadapp.keyvaluestore;

import com.example.multithreadapp.keyvaluestore.command.*;

public class Invoker {

    public void invokeCommand(Icommand command) {
        command.execute();
    }
}
