package se.salemcreative.starwars.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.salemcreative.starwars.model.Film;
import se.salemcreative.starwars.service.FilmService;

import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    @Autowired
    FilmService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Film> findAll() {
        log.info("Returning all films");
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Film getById(@PathVariable("id") Long id) {
        log.info("Returning film by id {}", id);
        return service.findById(id);
    }

}
