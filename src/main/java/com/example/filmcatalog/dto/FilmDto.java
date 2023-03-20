package com.example.filmcatalog.dto;

import com.example.filmcatalog.constants.GenreEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FilmDto {
    private String title;
    private GenreEnum genre;
    private Integer rating;

}
