package com.example.filmcatalog.controller;

import com.example.filmcatalog.service.DirectorService;
import com.example.filmcatalog.service.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("spring-security")
public class SecurityController {
    private final FilmService filmService;
    private final DirectorService directorService;

    @GetMapping("/hello")
    public String hello() {
        log.info("Hello!");
        return "hello";
    }

    @GetMapping("/admin")
    public String admin() {
        log.info("Admin");
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        log.info("User!");
        return "user";
    }
}
