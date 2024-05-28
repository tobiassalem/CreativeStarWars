package se.salemcreative.starwars.service;

import se.salemcreative.starwars.model.Character;
import se.salemcreative.starwars.model.Film;

import java.util.List;
import java.util.Set;

public interface FilmService {

    List<Film> findAll();

    Film findById(Long id);

    Film findByName(String name);

    Set<Film> findFilmsWithCharacter(String characterName);

}
