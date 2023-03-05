package com.example.filmcatalog;

import com.example.filmcatalog.constants.GenreEnum;
import com.example.filmcatalog.model.Director;
import com.example.filmcatalog.model.Film;
import com.example.filmcatalog.repository.DirectorRepository;
import com.example.filmcatalog.service.DirectorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DirectorControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private DirectorService directorService;
    private static HttpHeaders httpHeaders;

    @BeforeAll
    public static void init() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void addDirectorTest() throws JsonProcessingException {
        Director expectedDirector = new Director();
        expectedDirector.setName("Timofey");
        expectedDirector.setAge(19);
        expectedDirector.setCountry("Russia");
        expectedDirector.setFilmList(List.of(
                createFilm("Transformers", GenreEnum.ACTION, 5),
                createFilm("Shrek 1", GenreEnum.HORROR, 10),
                createFilm("Shrek 3", GenreEnum.ROMANCE, 10)));
        HttpEntity<String> httpEntity = new HttpEntity<>(new ObjectMapper().writeValueAsString(expectedDirector), httpHeaders);
        ResponseEntity<Director> directorResponseEntity =
                testRestTemplate.exchange(String.format("http://localhost:%d/director", port), HttpMethod.POST, httpEntity, Director.class);
        Director actualDirector = directorResponseEntity.getBody();


        AssertionsForClassTypes.assertThat(directorResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(actualDirector);
        assertNotNull(directorRepository.findById(actualDirector.getUuid()));
        assertNotNull(directorService.getDirector(actualDirector.getUuid()));
        AssertionsForClassTypes.assertThat(actualDirector.getFilmList().size()).isEqualTo(expectedDirector.getFilmList().size());
        assertNotNull(directorRepository.findById(actualDirector.getUuid()));
        assertThat(actualDirector.getName()).isEqualTo(expectedDirector.getName());
        assertThat(actualDirector.getAge()).isEqualTo(expectedDirector.getAge());
        assertThat(actualDirector.getCountry()).isEqualTo(expectedDirector.getCountry());

    }

    @Test
    void findDirectorTest() {
        Director expectedDirector = new Director();
        expectedDirector.setName("Grisha");
        expectedDirector.setAge(87);
        expectedDirector.setCountry("USA");
        expectedDirector.setFilmList(List.of(
                createFilm("Cars 1", GenreEnum.ACTION, 5),
                createFilm("Cars 2", GenreEnum.HORROR, 10),
                createFilm("Cars 3", GenreEnum.ROMANCE, 10)));
        directorRepository.save(expectedDirector);
        ResponseEntity<Director> directorResponseEntity =
                testRestTemplate.getForEntity(String.format("http://localhost:%d/director", port) + "?directorUuid={directorUuid}", Director.class, expectedDirector.getUuid());
        Director actualDirector = directorResponseEntity.getBody();
        assertThat(directorResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualDirector).isEqualTo(expectedDirector);


    }

    private Film createFilm(String title, GenreEnum genre, int rating) {
        Film film = new Film();
        film.setTitle(title);
        film.setRating(rating);
        film.setGenre(genre);
        return film;

    }
}
