package se.salemcreative.starwars.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.salemcreative.starwars.model.Character;

import java.util.Optional;


@Repository
public interface CharacterRepo extends JpaRepository<Character, Long> {

    Optional<Character> findByName(@Param("name") String name);

}
