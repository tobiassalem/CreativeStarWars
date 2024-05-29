package se.salemcreative.starwarsapi.service;

import se.salemcreative.starwarsapi.model.Film;

import java.util.List;
import java.util.Set;

public interface FilmService {

    List<Film> findAll();

    Film getById(Long id);

    Film getByTitle(String title);

    List<Film> findByTitle(String title);

    Set<Film> findFilmsWithCharacter(String characterName);

}
