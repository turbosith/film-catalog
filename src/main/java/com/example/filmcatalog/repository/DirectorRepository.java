package com.example.filmcatalog.repository;

import com.example.filmcatalog.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
