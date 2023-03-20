package com.example.filmcatalog.service;

import com.example.filmcatalog.dto.FilmDto;
import com.example.filmcatalog.model.Film;

import java.util.List;
import java.util.UUID;

public interface FilmService {
    FilmDto getFilm(UUID filmUuid);

    FilmDto saveFilm(Film film);

    List<Film> getAll();
}
