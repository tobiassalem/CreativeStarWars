
package se.salemcreative.starwarsapi.exception;

/**
 * Exception class for system exceptions in the StarWarsApi.
 * That is, runtime exceptions that normally should not occur.
 */
public class StarWarsApiSystemException extends StarWarsApiException {

    protected StarWarsApiSystemException() {
    }

    public StarWarsApiSystemException(String s) {
        super(s);
    }

    public StarWarsApiSystemException(Throwable throwable) {
        super(throwable);
    }

    public StarWarsApiSystemException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
