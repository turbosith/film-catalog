package com.example.filmcatalog.service.impl;

import com.example.filmcatalog.dto.UserDto;
import com.example.filmcatalog.mapping.UserMapper;
import com.example.filmcatalog.model.User;
import com.example.filmcatalog.repository.UserRepository;
import com.example.filmcatalog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    public void addUser(UserDto newUser) {
        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        repository.save(mapper.mapToEntity(newUser));
    }

    public List<UserDto> getAllUsers() {
        return repository.findAll().stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

    public UserDto getUser(String username) {
        User userEntity = repository.findByUsername(username).orElse(null);
        return userEntity == null ? null : mapper.mapToDto(userEntity);
    }
}
