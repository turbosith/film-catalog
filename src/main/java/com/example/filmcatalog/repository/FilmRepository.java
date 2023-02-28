package com.example.filmcatalog.repository;

import com.example.filmcatalog.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmRepository extends JpaRepository<Film, UUID> {
}
