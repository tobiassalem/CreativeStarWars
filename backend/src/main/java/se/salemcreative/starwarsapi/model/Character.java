package se.salemcreative.starwarsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import se.salemcreative.starwarsapi.exception.StarWarsApiUserException;

import java.time.Instant;
import java.util.*;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "height",
        "mass",
        "hair_color",
        "skin_color",
        "eye_color",
        "birth_year",
        "gender",
        "homeworld",
        "films",
        "species",
        "vehicles",
        "starships",
        "created",
        "edited",
        "url"
})
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 200)
    @JsonProperty("name")
    private String name;

    @JsonProperty("height")
    private String height;

    @JsonProperty("mass")
    private String mass;

    @JsonProperty("hair_color")
    private String hairColor;

    @JsonProperty("skin_color")
    private String skinColor;

    @JsonProperty("eye_color")
    private String eyeColor;

    @JsonProperty("birth_year")
    private String birthYear;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("homeworld")
    private String homeworld;

    //@JsonProperty("films")
    //private List<String> films;

    @JsonProperty("species")
    private List<String> species;

    @JsonProperty("vehicles")
    private List<String> vehicles;

    @JsonProperty("starships")
    private List<String> starships;

    @Transient
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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
            log.warn("You are already a cast of the film {}", f.getTitle());
            return;
        }

        log.info("Ok, Character {} is now part of the cast of the Film {}", this.name, f.getTitle());
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
