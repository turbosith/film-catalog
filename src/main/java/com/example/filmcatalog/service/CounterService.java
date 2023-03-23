package com.example.filmcatalog.service;

import java.util.concurrent.atomic.AtomicInteger;

public interface CounterService {
    public void increment();
    public AtomicInteger getValue();
}
