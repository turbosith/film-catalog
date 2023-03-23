package com.example.filmcatalog.controller;


import com.example.filmcatalog.service.impl.CounterServiceImpl;
import com.example.filmcatalog.service.impl.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/counter")
public class CounterController {
    @Autowired
    private CounterServiceImpl counterService;
    private static final int amountThreads = 3;

    @GetMapping("/multithreaded")
    public String counter() {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < amountThreads; i++) {
            threads.add(new ThreadService(counterService));
            threads.get(i).start();
        }

        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return counterService.getValue().toString();
    }
}
