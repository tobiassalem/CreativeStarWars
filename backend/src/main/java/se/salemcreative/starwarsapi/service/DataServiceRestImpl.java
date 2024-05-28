package se.salemcreative.starwarsapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import se.salemcreative.starwarsapi.dto.ApiResponseFilms;
import se.salemcreative.starwarsapi.jpa.CharacterRepo;
import se.salemcreative.starwarsapi.jpa.FilmRepo;
import se.salemcreative.starwarsapi.dto.ApiResponseCharacters;

import java.time.Duration;
import java.util.Collections;

@Service
@Slf4j
public class DataServiceRestImpl implements DataService {

    private static final long MAX_BLOCK_DURATION_IN_SECONDS = 10L;
    @Autowired
    WebClient webClient;

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

        ApiResponseCharacters response = webClient.get()
                .uri("/people/?format=json")
                .retrieve()
                .bodyToMono(ApiResponseCharacters.class)
                .doOnError(throwable -> log.error("Failed to get Characters from API: " + throwable))
                .block(Duration.ofSeconds(MAX_BLOCK_DURATION_IN_SECONDS));

        log.info("Got API response: {}. Characters: {}", response, response.getResults());
        if (response.getResults() == null || response.getResults().isEmpty()) {
            log.warn("Got no Characters from the API - nothing to persist.");
            return;
        }

        characterRepo.saveAll(response.getResults());
        log.info("Persisted {} nr of characters.", characterRepo.count());
    }

    private void initFilms() {

        ApiResponseFilms response = webClient.get()
                .uri("/films/?format=json")
                .retrieve()
                .bodyToMono(ApiResponseFilms.class)
                .doOnError(throwable -> log.error("Failed to get Films from API: " + throwable))
                .block(Duration.ofSeconds(MAX_BLOCK_DURATION_IN_SECONDS));

        log.info("Got API response: {}. Films: {}", response, response.getResults());
        if (response.getResults() == null || response.getResults().isEmpty()) {
            log.warn("Got no Films from the API - nothing to persist.");
            return;
        }

        filmRepo.saveAll(response.getResults());
        log.info("Persisted {} nr of films.", filmRepo.count());
    }

    //@Bean
    private WebClient getWebClient() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();

        return WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .exchangeStrategies(strategies)
                .build();
    }
}
