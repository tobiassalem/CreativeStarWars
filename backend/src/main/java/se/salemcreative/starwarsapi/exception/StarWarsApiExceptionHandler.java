package se.salemcreative.starwarsapi.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class handling our exceptions.
 */
@ControllerAdvice
@Slf4j
public class StarWarsApiExceptionHandler {

    @ExceptionHandler(StarWarsApiUserException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    StarWarsApiResponse handleException(final StarWarsApiUserException e) {
        log.error("---> StarWarsApiUserException: {}", e.getMessage());
        return new StarWarsApiResponse(e.getMessage(), e.getCause());
    }

    @ExceptionHandler(StarWarsApiSystemException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody
    StarWarsApiResponse handleException(final StarWarsApiSystemException e) {
        log.error("---> StarWarsApiSystemException: {}", e.getMessage());
        return new StarWarsApiResponse(e.getMessage(), e.getCause());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    StarWarsApiResponse handleException(final Exception e) {
        log.error("---> Unknown exception caught: {}", e);
        return new StarWarsApiResponse(e.getMessage(), e.getCause());
    }

}
