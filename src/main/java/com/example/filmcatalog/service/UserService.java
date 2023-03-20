package com.example.filmcatalog.service;

import com.example.filmcatalog.dto.UserDto;

import java.util.List;

public interface UserService {
    void addUser(UserDto newUser);

    List<UserDto> getAllUsers();

    UserDto getUser(String username);
}
