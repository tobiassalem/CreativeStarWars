package se.salemcreative.starwarsapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import se.salemcreative.starwarsapi.service.CharacterService;
import se.salemcreative.starwarsapi.model.Character;

import java.util.List;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class CharacterController {

    @Autowired
    CharacterService service;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Character> findAll() {
        log.info("Returning all characters");
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Character getById(@PathVariable("id") Long id) {
        log.info("Returning Character by id {}", id);
        return service.getById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Character> findByName(@PathVariable("name") String name) {
        log.info("Finding Characters by name {}", name);
        return service.findByName(name);
    }
}
