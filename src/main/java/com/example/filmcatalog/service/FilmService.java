package com.example.filmcatalog.service;

import com.example.filmcatalog.model.Film;

import java.util.UUID;

public interface FilmService {
    Film getFilm(UUID filmUuid);

    Film saveFilm(Film film);
}
