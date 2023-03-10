package com.example.filmcatalog.controller;

import com.example.filmcatalog.model.Director;
import com.example.filmcatalog.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/director")
public class DirectorController {
    private final DirectorService directorService;

    @PostMapping
    public Director addDirector(@RequestBody Director director) {
        return directorService.saveDirector(director);
    }

    @GetMapping
    public Director getDirector(@RequestParam("directorUuid") UUID directorUuid) {
        return directorService.getDirector(directorUuid);
    }
}
