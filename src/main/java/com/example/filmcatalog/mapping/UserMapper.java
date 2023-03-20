package com.example.filmcatalog.mapping;

import com.example.filmcatalog.dto.UserDto;
import com.example.filmcatalog.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToDto(User user);

    User mapToEntity(UserDto userDto);
}
