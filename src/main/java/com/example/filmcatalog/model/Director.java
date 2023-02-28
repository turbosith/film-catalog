package com.example.filmcatalog.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String name;
    private Integer age;
    private String country;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Film> filmList;
}
