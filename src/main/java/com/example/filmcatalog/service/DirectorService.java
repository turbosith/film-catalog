package com.example.filmcatalog.service;

import com.example.filmcatalog.dto.DirectorDto;

import java.util.UUID;

public interface DirectorService {
    DirectorDto getDirector(UUID directorUuid);

    void saveDirector(DirectorDto director);
}
