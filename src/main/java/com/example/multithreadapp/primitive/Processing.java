package com.example.multithreadapp.primitive;

import java.util.ArrayList;
import java.util.List;

public class Processing implements Runnable{

    int count;
    List<Model> modelList = new ArrayList<>();
    public Processing() {
        this.count = 1;
    }


    @Override
    public void run() {
        for (int i = 1; i <= 1000000000 ; i++) {

            this.count = this.count + 1;
            Model model = new Model(this.count);
            modelList.add(model);

            try {
//                Thread.sleep(0,2);
                System.out.println(Thread.currentThread().getName() + " " + this.count);
                System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
            }
            catch (Exception e) {
                System.out.println(String.valueOf(e));
            }
        }
    }


}
