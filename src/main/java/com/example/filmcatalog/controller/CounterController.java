package com.example.filmcatalog.controller;


import com.example.filmcatalog.service.impl.CounterServiceImpl;
import com.example.filmcatalog.service.impl.ThreadService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;




@RestController
@RequestMapping("/counter")
@NoArgsConstructor
@Slf4j
public class CounterController  {
    @Autowired
    private CounterServiceImpl counterService;
    public static final String COUNTER_CONTROLLER="counterController";
    private static final int amountThreads = 3;
    @GetMapping("/multithreaded")
    @CircuitBreaker(name = COUNTER_CONTROLLER,fallbackMethod = "fallBack")
    public String counter() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < amountThreads; i++) {
            threads.add(new ThreadService(counterService));
            threads.get(i).start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return counterService.getValue().toString();
    }


    public String fallBack() {

        log.info("Some message");
        return "circuit breaker";
    }

}
