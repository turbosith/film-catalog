package com.example.filmcatalog.service;

import com.example.filmcatalog.model.Director;

import java.util.UUID;

public interface DirectorService {
    Director getDirector(UUID directorUuid);

    Director saveDirector(Director director);
}
