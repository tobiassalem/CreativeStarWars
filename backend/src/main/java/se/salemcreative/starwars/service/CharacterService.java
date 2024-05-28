package se.salemcreative.starwars.service;

import se.salemcreative.starwars.model.Character;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    List<Character> findAll();

    Character findById(Long id);

    Character findByName(String name);

    Set<Character> findFilmCast(String filmName);

}
