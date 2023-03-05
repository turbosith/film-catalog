package com.example.filmcatalog;

import com.example.filmcatalog.constants.GenreEnum;
import com.example.filmcatalog.model.Film;
import com.example.filmcatalog.repository.FilmRepository;
import com.example.filmcatalog.service.FilmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class FilmControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmService filmService;
    private static HttpHeaders httpHeaders;

    @BeforeAll
    public static void init() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void addFilmTest() throws JsonProcessingException {
        Film expectedFilm = new Film();
        UUID uuid = UUID.randomUUID();
        expectedFilm.setUuid(uuid);
        expectedFilm.setGenre(GenreEnum.ROMANCE);
        expectedFilm.setRating(9);
        expectedFilm.setTitle("Star Wars: Revenge of the Sith (Episode III)");
        HttpEntity<String> httpEntity =
                new HttpEntity<>(new ObjectMapper().writeValueAsString(expectedFilm), httpHeaders);
        ResponseEntity<Film> filmResponseEntity =
                testRestTemplate.exchange(String.format("http://localhost:%d/film", port),
                        HttpMethod.POST, httpEntity, Film.class);
        Film actualFilm = filmResponseEntity.getBody();
        assertNotNull(actualFilm);
        assertThat(filmResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(filmRepository.findById(actualFilm.getUuid()));
        assertNotNull(filmService.getFilm(actualFilm.getUuid()));
        assertThat(actualFilm.getTitle()).isEqualTo(expectedFilm.getTitle());
        assertThat(actualFilm.getUuid()).isNotEqualTo(expectedFilm.getUuid());
        assertThat(actualFilm.getGenre()).isEqualTo(expectedFilm.getGenre());
        assertThat(actualFilm.getRating()).isEqualTo(expectedFilm.getRating());

    }

    @Test
    void findFilmTest() {
        Film expectedFilm = new Film();
        expectedFilm.setGenre(GenreEnum.ACTION);
        expectedFilm.setRating(10);
        expectedFilm.setTitle("Shrek 2");
        filmRepository.save(expectedFilm);
        ResponseEntity<Film> filmResponseEntity =
                testRestTemplate.getForEntity(String.format("http://localhost:%d/film", port)
                        + "?filmUuid={filmUuid}", Film.class, expectedFilm.getUuid());
        Film actualFilm = filmResponseEntity.getBody();
        assertThat(filmResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualFilm).isEqualTo(expectedFilm);


    }


}
