package com.example.filmcatalog.controller;

import com.example.filmcatalog.dto.DirectorDto;
import com.example.filmcatalog.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/director")
public class DirectorController {
    private final DirectorService directorService;

    @PostMapping
    public void addDirector(@RequestBody DirectorDto director) {
        directorService.saveDirector(director);
    }

    @GetMapping
    public DirectorDto getDirector(@RequestParam("directorUuid") UUID directorUuid) {
        return directorService.getDirector(directorUuid);
    }

    @GetMapping("/add")
    public String newDirector(Model model) {
        model.addAttribute("newDirector", new DirectorDto());
        return "new-director";
    }

    @PostMapping("/new")
    public void addNewDirector(@ModelAttribute("newDirector") DirectorDto director) {
        directorService.saveDirector(director);
    }

}
