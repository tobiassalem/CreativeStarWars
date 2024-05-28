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

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "episode_id",
        "opening_crawl",
        "director",
        "producer",
        "release_date",
        "characters",
        "planets",
        "starships",
        "vehicles",
        "species",
        "created",
        "edited",
        "url"
})
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

    @JsonProperty("title")
    private String title;

    @JsonProperty("episode_id")
    private Integer episodeId;

    @JsonProperty("opening_crawl")
    @Column(columnDefinition="TEXT")
    private String openingCrawl;

    @JsonProperty("director")
    private String director;

    @JsonProperty("producer")
    private String producer;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("characters")
    private List<String> characters;

    @JsonProperty("planets")
    private List<String> planets;

    @JsonProperty("starships")
    private List<String> starships;

    @JsonProperty("vehicles")
    private List<String> vehicles;

    @JsonProperty("species")
    private List<String> species;

    @JsonProperty("created")
    private String created;

    @JsonProperty("edited")
    private String edited;

    @JsonProperty("url")
    private String url;

    @Transient
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    private Instant createdAt;

    private Instant updatedAt;

    public Film(String name) {
        this.title = name;
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
        log.info("LoadCount for {} is now {}, castCount is {}", title, newCount, castCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film other = (Film) o;
        return title.equals(other.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", filmCastCount= " + filmCast.size() +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
