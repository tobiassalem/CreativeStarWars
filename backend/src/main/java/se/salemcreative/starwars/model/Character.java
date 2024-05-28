package se.salemcreative.starwars.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.salemcreative.starwars.exception.StarWarsApiUserException;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Backtick names that are reserved words
 * Ref. https://www.h2database.com/html/advanced.html#keywords
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`Character`")
@Slf4j
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 200)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "filmCast")
    private Set<Film> films = new HashSet<>();

    private Instant createdAt;

    private Instant updatedAt;

    @Transient
    private final AtomicLong visitCount = new AtomicLong(0);

    public Character(String name) {
        this.name = name;
    }

    /* ================================== [Accessors] ============================================================== */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }


    /* ================================== [Logic] ================================================================= */

    public Long getVisitCount() {
        return visitCount.get();
    }

    public void addFilm(Film f) {
        if (f == null) {
            log.error("Character {} tried to add himself/herself as cast to a non-existent film!", name);
            throw new StarWarsApiUserException("You can only be a ca of valid films! Given film does not exist.");
        }
        if (films.contains(f)) {
            log.warn("You are already a cast of the film {}", f.getName());
            return;
        }

        log.info("Ok, Character {} is now part of the cast of the Film {}", this.name, f.getName());
        films.add(f);
    }

    /* ================================== [Helpers] =============================================================== */

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    /**
     * This callback hook method is called right after the entity is loaded.
     * We use it to load any JPA relations that are not fetched eagerly.
     * Concretely this solves the Hibernate limitation that it is not possible to eagerly load multiple collections.
     */
    @PostLoad
    public void initRelations() {
        this.films.size();
        long newCount = visitCount.incrementAndGet();
        log.info("VisitCount for {} is now {}", name, newCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character otherCharacter = (Character) o;
        return name.equals(otherCharacter.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filmCount= " + films.size() +
                ", visitCount= " + getVisitCount() +
                '}';
    }

}
