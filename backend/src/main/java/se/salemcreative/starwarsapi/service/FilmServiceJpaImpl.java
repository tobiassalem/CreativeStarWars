package se.salemcreative.starwarsapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.salemcreative.starwarsapi.exception.StarWarsApiSystemException;
import se.salemcreative.starwarsapi.jpa.FilmRepo;
import se.salemcreative.starwarsapi.model.Film;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class FilmServiceJpaImpl implements FilmService {

    @Autowired
    FilmRepo repo;

    @Override
    public List<Film> findAll() {
        return repo.findAll();
    }

    @Override
    public Film getById(Long id) {
        Optional<Film> byId = repo.findById(id);
        if (byId.isEmpty()) {
            throw new StarWarsApiSystemException("Film with id " + id + " does not exist.");
        }

        return byId.get();
    }

    @Override
    public Film getByTitle(String title) {
        Optional<Film> byTitle = repo.findByTitle(title);
        if (byTitle.isEmpty()) {
            throw new StarWarsApiSystemException("Film with title " + title + " does not exist.");
        }

        return byTitle.get();
    }

    @Override
    public List<Film> findByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public Set<Film> findFilmsWithCharacter(String characterName) {
        return new HashSet<>();
    }
}
