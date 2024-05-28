package se.salemcreative.starwars.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salemcreative.starwars.exception.StarWarsApiSystemException;
import se.salemcreative.starwars.jpa.FilmRepo;
import se.salemcreative.starwars.model.Character;
import se.salemcreative.starwars.model.Film;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class FilmServiceJpaImpl implements FilmService {

    @Autowired
    FilmRepo filmRepo;
    @Override
    public List<Film> findAll() {
        return filmRepo.findAll();
    }

    @Override
    public Film findById(Long id) {
        Optional<Film> byId = filmRepo.findById(id);
        if (byId.isEmpty()) {
            throw new StarWarsApiSystemException("Film with id " + id + " does not exist.");
        }

        return byId.get();
    }

    @Override
    public Film findByName(String name) {
        return null;
    }

    @Override
    public Set<Film> findFilmsWithCharacter(String characterName) {
        return null;
    }
}
