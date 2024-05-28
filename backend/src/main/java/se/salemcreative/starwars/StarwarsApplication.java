package se.salemcreative.starwars;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import se.salemcreative.starwars.exception.StarWarsApiSystemException;
import se.salemcreative.starwars.jpa.CharacterRepo;
import se.salemcreative.starwars.jpa.FilmRepo;
import se.salemcreative.starwars.model.Character;
import se.salemcreative.starwars.model.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Slf4j
public class StarwarsApplication {

	private static final List<String> CHARACTER_NAMES = List.of("Luke Skywalker", "Leia",
			"Han Solo", "Darth Vader");

	private static final List<String> FILM_NAMES = List.of(
			"Episode I – The Phantom Menace", "Episode II – Attack of the Clones", "Episode III – Revenge of the Sith",
			"Episode IV – A New Hope", "Episode V – The Empire Strikes Back", "Episode VI – Return of the Jedi",
			"Episode VII – The Force Awakens", "Episode VIII – The Last Jedi", "Episode IX – The Rise of Skywalker");

	@Autowired
	CharacterRepo characterRepo;

	@Autowired
	FilmRepo filmRepo;

	public static void main(String[] args) {
		SpringApplication.run(StarwarsApplication.class, args);
	}

	@PostConstruct
	@Transactional
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

		Optional<Film> episodeFour = filmRepo.findByName("Episode IV – A New Hope");
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
