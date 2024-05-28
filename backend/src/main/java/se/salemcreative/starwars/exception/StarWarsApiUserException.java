package se.salemcreative.starwars.exception;

/**
 * Exception class for user exceptions in the StarWarsApi.
 * That is, exceptions caused by users of the system. This can be any user: man or machine.
 */
public class StarWarsApiUserException extends StarWarsApiException {

    public StarWarsApiUserException(String s) {
        super(s);
    }

    public StarWarsApiUserException(String s, Throwable throwable) {
        super(s, throwable);
    }

}


