package com.example.filmcatalog.service.impl;

import com.example.filmcatalog.service.CounterService;

public class CounterServiceImpl implements CounterService {
    private static int counter=0;
    @Override
    public void increment() {
        counter++;

    }

    @Override
    public int getValue() {
        return counter;
    }
}
