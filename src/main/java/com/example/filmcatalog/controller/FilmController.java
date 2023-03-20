package com.example.filmcatalog.controller;

import com.example.filmcatalog.dto.FilmDto;
import com.example.filmcatalog.model.Film;
import com.example.filmcatalog.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/film")
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    public FilmDto addFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }

    @GetMapping
    public FilmDto getDFilm(@RequestParam("filmUuid") UUID filmUuid) {
        return filmService.getFilm(filmUuid);
    }

    @GetMapping("/view")
    public String viewAll(Model model) {
        List<Film> films = filmService.getAll();
        model.addAttribute("listOfFilms", films);
        return "films";
    }
}

