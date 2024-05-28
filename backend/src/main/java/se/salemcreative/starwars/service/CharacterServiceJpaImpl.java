package se.salemcreative.starwars.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salemcreative.starwars.exception.StarWarsApiSystemException;
import se.salemcreative.starwars.exception.StarWarsApiUserException;
import se.salemcreative.starwars.jpa.CharacterRepo;
import se.salemcreative.starwars.jpa.FilmRepo;
import se.salemcreative.starwars.model.Character;
import se.salemcreative.starwars.model.Film;

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
    public Character findById(Long id) {
        Optional<Character> byId = characterRepo.findById(id);
        if (byId.isEmpty()) {
            throw new StarWarsApiSystemException("Character with id " + id + " does not exist.");
        }

        return byId.get();
    }

    @Override
    public Character findByName(String name) {
        Optional<Character> byName = characterRepo.findByName(name);
        if (byName.isEmpty()) {
            throw new StarWarsApiUserException("Character with name " + name + " does not exist.");
        }

        return byName.get();
    }

    @Override
    public Set<Character> findFilmCast(String filmName) {
        Optional<Film> byName = filmRepo.findByName(filmName);
        if (byName.isEmpty()) {
            throw new StarWarsApiUserException("Film with name " + filmName + " does not exist.");
        }

        return byName.get().getFilmCast();
    }

}
