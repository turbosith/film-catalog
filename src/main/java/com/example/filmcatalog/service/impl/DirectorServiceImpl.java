package com.example.filmcatalog.service.impl;

import com.example.filmcatalog.dto.DirectorDto;
import com.example.filmcatalog.dto.UserDto;
import com.example.filmcatalog.mapping.DirectorMapper;
import com.example.filmcatalog.model.Director;
import com.example.filmcatalog.model.User;
import com.example.filmcatalog.repository.DirectorRepository;
import com.example.filmcatalog.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;
    private final DirectorMapper mapper;


    @Override
    public DirectorDto getDirector(UUID directorUuid) {

        Optional<Director> directorOptional = directorRepository.findById(directorUuid);
        return mapper.mapToDto(directorOptional);
    }

    @Override
    public void saveDirector(DirectorDto director) {
            Director directorEntity = mapper.mapToEntity(director);
            directorRepository.save(directorEntity);
        }
    }

