package se.salemcreative.starwarsapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import se.salemcreative.starwarsapi.exception.StarWarsApiSystemException;
import se.salemcreative.starwarsapi.jpa.CharacterRepo;
import se.salemcreative.starwarsapi.jpa.FilmRepo;
import se.salemcreative.starwarsapi.model.Character;
import se.salemcreative.starwarsapi.model.Film;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Profile("none")
public class DataServiceMockImpl implements DataService {

    private static final List<String> CHARACTER_NAMES = List.of(
            "Luke Skywalker", "Leia Organa", "Han Solo", "Darth Vader", "Owen Lars", "C-3PO", "R2-D2");

    private static final List<String> FILM_NAMES = List.of(
            "Episode I – The Phantom Menace", "Episode II – Attack of the Clones", "Episode III – Revenge of the Sith",
            "Episode IV – A New Hope", "Episode V – The Empire Strikes Back", "Episode VI – Return of the Jedi",
            "Episode VII – The Force Awakens", "Episode VIII – The Last Jedi", "Episode IX – The Rise of Skywalker");

    @Autowired
    CharacterRepo characterRepo;

    @Autowired
    FilmRepo filmRepo;

    @Override
    public void initData() {
        initCharacters();
        initFilms();
    }

    private void initCharacters() {
        for (String name : CHARACTER_NAMES) {
            characterRepo.save(new Character(name));
        }
        log.info("Persisted {} nr of characters.", characterRepo.count());
    }

    private void initFilms() {

        for (String name : FILM_NAMES) {
            filmRepo.save(new Film(name));
        }
        log.info("Persisted {} nr of films.", filmRepo.count());

        Optional<Film> episodeFour = filmRepo.findByTitle("Episode IV – A New Hope");
        if (episodeFour.isEmpty()) {
            throw new StarWarsApiSystemException("Unexpected this is! Film  Episode IV – A New Hope does not exist.");
        }

        Film film = episodeFour.get();
        for (String name : CHARACTER_NAMES) {
            Character character = characterRepo.findByName(name).get();
            film.addFilmCast(character);
        }
        filmRepo.save(film);
    }

}
