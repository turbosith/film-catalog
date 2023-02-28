package com.example.filmcatalog.controller;

import com.example.filmcatalog.model.Director;
import com.example.filmcatalog.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/director")
public class DirectorController {
    private final DirectorService directorService;
    @PostMapping
    public ResponseEntity<?> addDirector(@RequestBody Director director){
        Director resultDirector = directorService.saveDirector(director);
        return ResponseEntity.ok(resultDirector);
    }
    @GetMapping
    public ResponseEntity<?> getDirector(@RequestParam("directorUuid")UUID directorUuid){
        Director director = directorService.getDirector(directorUuid);
        return ResponseEntity.ok(director);
    }
}
