package com.example.filmcatalog;

import com.example.filmcatalog.constants.GenreEnum;
import com.example.filmcatalog.model.Director;
import com.example.filmcatalog.model.Film;
import com.example.filmcatalog.repository.DirectorRepository;
import com.example.filmcatalog.service.DirectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DirectorControllerTests {
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private DirectorService directorService;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void resetDb() {
        directorRepository.deleteAll();
    }

    @Test
    public void saveDirectorTest() throws Exception {
        Director director = new Director();
        director.setName("Grisha");
        director.setAge(87);
        director.setCountry("USA");
        director.setFilmList(List.of(
                createFilm("Cars 1", GenreEnum.ACTION, 5),
                createFilm("Cars 2", GenreEnum.HORROR, 10),
                createFilm("Cars 3", GenreEnum.ROMANCE, 10)));
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        post("/director")
                                .content(objectMapper.writeValueAsString(director))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.name").value("Grisha"))
                .andExpect(jsonPath("$.age").value(87))
                .andExpect(jsonPath("$.country").value("USA"))
                .andExpect(jsonPath("$.filmList").isArray())
        ;
    }

    @Test
    public void addDirectorTest() throws Exception {
        UUID uuid = createTestDirector("Timofey", 19, "Russia").getUuid();
        mockMvc.perform(
                        get("/director?directorUuid={uuid}", uuid.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()))
                .andExpect(jsonPath("$.name").value("Timofey"))
                .andExpect(jsonPath("$.age").value(19))
                .andExpect(jsonPath("$.country").value("Russia"));
    }

    private Director createTestDirector(String name, int age, String country) {
        Director director = new Director();
        director.setName(name);
        director.setAge(age);
        director.setCountry(country);
        return directorService.saveDirector(director);
    }

    private Film createFilm(String title, GenreEnum genre, int rating) {
        Film film = new Film();
        film.setTitle(title);
        film.setRating(rating);
        film.setGenre(genre);
        return film;

    }

}
