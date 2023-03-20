package com.example.filmcatalog.service.impl;

import com.example.filmcatalog.dto.FilmDto;
import com.example.filmcatalog.mapping.FilmMapper;
import com.example.filmcatalog.model.Film;
import com.example.filmcatalog.repository.FilmRepository;
import com.example.filmcatalog.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final FilmMapper mapper;

    @Override
    public FilmDto getFilm(UUID filmUuid) {
        Optional<Film> filmOptional = filmRepository.findById(filmUuid);
        return mapper.mapToDto(filmOptional);
    }

    @Override
    public FilmDto saveFilm(Film film) {
        Film filmSave = filmRepository.save(film);
        return mapper.mapToDto(Optional.of(filmSave));
    }

    @Override
    public List<Film> getAll() {
        List<Film> films = filmRepository.findAll();
     return films;
    }
}
