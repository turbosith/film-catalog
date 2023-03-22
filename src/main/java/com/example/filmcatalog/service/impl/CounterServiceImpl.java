package com.example.filmcatalog.service.impl;

import com.example.filmcatalog.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CounterServiceImpl implements CounterService {
    private AtomicInteger counter=new AtomicInteger(0);
    @Override
    public void increment() {
        counter.incrementAndGet();

    }

    @Override
    public AtomicInteger getValue() {
        return counter;
    }
}
