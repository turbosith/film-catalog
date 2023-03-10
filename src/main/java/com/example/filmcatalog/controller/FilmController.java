package com.example.filmcatalog.controller;

import com.example.filmcatalog.model.Film;
import com.example.filmcatalog.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    public Film addFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }

    @GetMapping
    public Film getDFilm(@RequestParam("filmUuid") UUID filmUuid) {
        return filmService.getFilm(filmUuid);
    }
}

