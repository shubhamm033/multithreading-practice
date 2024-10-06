package com.example.multithreadapp.keyvaluestore;

import com.example.multithreadapp.keyvaluestore.exceptions.MyRuntimeException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Storage<K, V>  {


    Map<K, V> store;
    Boolean isTransaction = false;
    Stack<Operation> operations = null;


    public Storage() {
        store = new HashMap<>();
    }

    public void add(K key, V value) {

        if (isTransaction) {
            operations.add(new Operation("ADD", key, null));
        }
        store.put(key, value);
        show();
    }


    public V get(K key) throws MyRuntimeException {

        if (!store.containsKey(key)) {
            throw new MyRuntimeException("Item does not exist for the key");
        }
        else {
            return store.get(key);
        }
    }


    public void update(K key, V value) {
        if (!store.containsKey(key)) {
            throw new MyRuntimeException("Item does not exist for the key");
        }
        else {
            if (isTransaction) {
                operations.add(new Operation("UPDATE", key, store.get(key)));
            }
            store.put(key, value);
        }
        show();
    }


    public void delete(K key) throws MyRuntimeException {
        if (!store.containsKey(key)) {
            throw new MyRuntimeException("Item does not exist for the key");
        }

        else{
            if (isTransaction) {
                operations.add(new Operation("DELETE", key, store.get(key)));
            }
            store.remove(key);
        }
        show();
    }


    public void begin() {
        if (!isTransaction) {
            isTransaction = true;
            operations = new Stack<>();
            System.out.println("Transaction stated");
            show();
        }
        else {
            throw new RuntimeException("One transaction already in progress");

        }

    }



    public void commit() {
        isTransaction = false;
        while (!operations.isEmpty()) {
            operations.pop();
        }
        System.out.println("Transaction committed");
        show();
    }


    public void rollback() {
        isTransaction = false;

        while (!operations.isEmpty()) {
            Operation operation = operations.pop();
            if (operation.name.equals("ADD")) {
                store.remove(operation.key);
            }
            else if (operation.name.equals("UPDATE")) {
                store.put((K) operation.key, (V) operation.value);
            }
            else if (operation.name.equals("DELETE")) {
                store.put((K) operation.key, (V) operation.value);
            }
        }
        System.out.println("Transaction rollback");
        show();

    }


    class Operation<K, V> {

        String name;
        K key;
        V value;


        public Operation(String name, K key, V value) {
            this.name = name;
            this.key = key;
            this.value = value;
        }
    }

    public void show() {
        System.out.println(store.toString());
    }

}
