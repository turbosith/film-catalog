package com.example.filmcatalog.mapping;

import com.example.filmcatalog.dto.FilmDto;
import com.example.filmcatalog.model.Film;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    FilmDto mapToDto(Optional<Film> film);

    Film mapToEntity( FilmDto filmDto);
}
