package com.example.filmcatalog;

import com.example.filmcatalog.constants.GenreEnum;
import com.example.filmcatalog.model.Film;
import com.example.filmcatalog.repository.FilmRepository;
import com.example.filmcatalog.service.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerTests {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmService filmService;


    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        filmRepository.deleteAll();
    }

    @Test
    public void saveFilmTest() throws Exception {
        Film film = new Film();
        film.setGenre(GenreEnum.ROMANCE);
        film.setRating(9);
        film.setTitle("Star Wars: Revenge of the Sith (Episode III)");
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        post("/film")
                                .content(objectMapper.writeValueAsString(film))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.title").value("Star Wars: Revenge of the Sith (Episode III)"))
                .andExpect(jsonPath("$.rating").value(9))
                .andExpect(jsonPath("$.genre").value(GenreEnum.ROMANCE.toString()))
        ;
    }

    @Test
    public void addFilmTest() throws Exception {
        UUID uuid = createTestFilm("Shrek 2", 10, GenreEnum.ACTION).getUuid();
        mockMvc.perform(
                        get("/film?filmUuid={uuid}", uuid.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.title").value("Shrek 2"))
                .andExpect(jsonPath("$.rating").value(10))
                .andExpect(jsonPath("$.genre").value(GenreEnum.ACTION.toString()));
    }

    private Film createTestFilm(String title, int rating, GenreEnum genre) {
        Film film = new Film();
        film.setTitle(title);
        film.setRating(rating);
        film.setGenre(genre);
        return filmService.saveFilm(film);
    }


}
