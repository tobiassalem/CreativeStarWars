package se.salemcreative.starwarsapi.exception;

import org.springframework.lang.Nullable;

/**
 * Exception class for any event that occurs in the StarWarsApi.
 */
public abstract class StarWarsApiException extends RuntimeException {

    StarWarsApiException() {
    }

    StarWarsApiException(String s) {
        super(s);
    }

    StarWarsApiException(Throwable throwable) {
        super(throwable);
    }

    StarWarsApiException(String s, Throwable throwable) {
        super(s, throwable);
    }

}