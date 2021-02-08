package hu.flow.springtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationException extends ResponseStatusException {
    public ValidationException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
