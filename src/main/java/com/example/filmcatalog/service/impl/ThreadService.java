package com.example.filmcatalog.service.impl;

public class ThreadService extends Thread{
    CounterServiceImpl counterService;
    public ThreadService(CounterServiceImpl counterService){
        this.counterService=counterService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            counterService.increment();
        }
    }
}
