package se.salemcreative.starwarsapi.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.salemcreative.starwarsapi.model.Character;

import java.util.List;
import java.util.Optional;

/**
 * Repo for our Characters
 */
@Repository
public interface CharacterRepo extends JpaRepository<Character, Long> {

    Optional<Character> findByName(@Param("name") String name);

    List<Character> findByNameContainingIgnoreCase(String message);

}
