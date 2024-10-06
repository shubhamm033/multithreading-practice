package com.example.multithreadapp.keyvaluestore;

import com.example.multithreadapp.keyvaluestore.command.*;

public class Driver {

    public static void main(String[] args) {

        Storage storage = new Storage<String, String>();
        Invoker invoker = new Invoker();

        Icommand addcommand = new AddCommand(storage, "key1", "value1");
        Icommand addcommand2 = new AddCommand(storage, "key2", "value2");
        Icommand begincommand = new BeginCommand(storage);
        Icommand getcommand = new GetCommand(storage, "key1");
        Icommand commitcommand = new CommitCommand(storage);
        Icommand setcommand = new SetCommand(storage, "key1", "value3");
        Icommand rollbackcommand = new RollbackCommand(storage);
        Icommand deletecommand = new DeleteCommand(storage, "key2");
        Icommand getcommandTwo = new GetCommand(storage, "key2");

        invoker.invokeCommand(begincommand);
        invoker.invokeCommand(addcommand);
        invoker.invokeCommand(addcommand2);
        invoker.invokeCommand(getcommand);
        invoker.invokeCommand(commitcommand);

        invoker.invokeCommand(begincommand);
        invoker.invokeCommand(setcommand);
        invoker.invokeCommand(deletecommand);
        invoker.invokeCommand(rollbackcommand);

        invoker.invokeCommand(getcommand);
        invoker.invokeCommand(getcommandTwo);

    }
}
