package se.salemcreative.starwars.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.salemcreative.starwars.model.Character;
import se.salemcreative.starwars.model.Film;

import java.util.List;
import java.util.Optional;

/**
 * Repo for our Films
 * <p>
 * Ref. https://www.objectdb.com/java/jpa/query/jpql/string#like_-_string_pattern_matching_with_wildcards
 * On counting entities & data.
 * Ref. https://stackoverflow.com/questions/10696490/does-spring-data-jpa-have-any-way-to-count-entites-using-method-name-resolving
 */
@Repository
public interface FilmRepo extends JpaRepository<Film, Long>, JpaSpecificationExecutor<Film> {

    Optional<Film> findByName(@Param("name") String name);

    List<Film> findAllByOrderByCreatedAtDesc();

    List<Film> findAllByOrderByUpdatedAtDesc();

}
