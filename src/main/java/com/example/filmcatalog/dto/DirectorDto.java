package com.example.filmcatalog.dto;

import com.example.filmcatalog.model.Film;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DirectorDto {
    private String name;
    private Integer age;
    private String country;
    private List<Film> filmList;


}
