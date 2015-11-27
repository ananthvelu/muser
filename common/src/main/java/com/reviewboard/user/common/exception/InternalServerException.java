package com.reviewboard.user.common.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Internal Server Exception
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = -1790211652911971720L;

    public InternalServerException(String request) {
        super("Internal Server Exception :" + request);
    }
}