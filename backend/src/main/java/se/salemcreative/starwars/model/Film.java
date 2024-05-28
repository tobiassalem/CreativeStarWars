package se.salemcreative.starwars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "film_cast_relation",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<Character> filmCast = new HashSet<>();

    @Transient
    private Integer castCount = filmCast.size();

    @Transient
    private final AtomicLong loadCount = new AtomicLong(0);

    private String name;

    private Instant createdAt;

    private Instant updatedAt;

    public Film(String name) {
        this.name = name;
    }

    /* ================================== [Logic] ================================================================= */

    public void addFilmCast(Character character) {
        filmCast.add(character);
        character.addFilm(this);
    }

    public Long getLoadCount() {
        return loadCount.get();
    }

    /* ================================== [Helpers] ================================================================ */

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
        this.filmCast.size();
        this.castCount = filmCast.size();
        long newCount = loadCount.incrementAndGet();
        log.info("LoadCount for {} is now {}, castCount is {}", name, newCount, castCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film other = (Film) o;
        return name.equals(other.getName());
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
                ", filmCastCount= " + filmCast.size() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
