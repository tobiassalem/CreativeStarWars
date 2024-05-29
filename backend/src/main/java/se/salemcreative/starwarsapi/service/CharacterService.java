package se.salemcreative.starwarsapi.service;

import se.salemcreative.starwarsapi.model.Character;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    List<Character> findAll();

    Character getById(Long id);

    Character getByName(String name);

    List<Character> findByName(String name);

    Set<Character> findFilmCast(String filmName);

}
