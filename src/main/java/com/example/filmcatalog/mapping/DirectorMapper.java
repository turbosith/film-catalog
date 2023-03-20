package com.example.filmcatalog.mapping;

import com.example.filmcatalog.dto.DirectorDto;
import com.example.filmcatalog.model.Director;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorDto mapToDto(Optional<Director> director);

    Director mapToEntity(DirectorDto userDto);
}
