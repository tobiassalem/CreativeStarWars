package se.salemcreative.starwars.exception;

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

    @Nullable
    public <T extends Exception> T getCause(Class<T> clazz) {
        for (Throwable e = this; e != null; e = e.getCause()) {
            if (clazz.isAssignableFrom(e.getClass())) {
                return (T) e;
            }
        }
        return null;
    }
}