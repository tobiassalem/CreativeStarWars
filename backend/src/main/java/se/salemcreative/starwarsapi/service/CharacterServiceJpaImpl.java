package se.salemcreative.starwarsapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salemcreative.starwarsapi.exception.StarWarsApiSystemException;
import se.salemcreative.starwarsapi.exception.StarWarsApiUserException;
import se.salemcreative.starwarsapi.jpa.CharacterRepo;
import se.salemcreative.starwarsapi.jpa.FilmRepo;
import se.salemcreative.starwarsapi.model.Character;
import se.salemcreative.starwarsapi.model.Film;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class CharacterServiceJpaImpl implements CharacterService {

    @Autowired
    CharacterRepo characterRepo;

    @Autowired
    FilmRepo filmRepo;

    public List<Character> findAll() {
        return characterRepo.findAll();
    }

    @Override
    public Character getById(Long id) {
        Optional<Character> byId = characterRepo.findById(id);
        if (byId.isEmpty()) {
            throw new StarWarsApiSystemException("Character with id " + id + " does not exist.");
        }

        return byId.get();
    }

    @Override
    public Character getByName(String name) {
        Optional<Character> byName = characterRepo.findByName(name);
        if (byName.isEmpty()) {
            throw new StarWarsApiUserException("Character with name " + name + " does not exist.");
        }

        return byName.get();
    }

    @Override
    public List<Character> findByName(String name) {
        return characterRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Set<Character> findFilmCast(String filmName) {
        Optional<Film> byName = filmRepo.findByTitle(filmName);
        if (byName.isEmpty()) {
            throw new StarWarsApiUserException("Film with name " + filmName + " does not exist.");
        }

        return byName.get().getFilmCast();
    }

}
