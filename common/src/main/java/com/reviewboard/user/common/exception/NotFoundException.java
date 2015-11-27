package com.reviewboard.user.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Not Found Exception
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = -1790211652911971729L;

    public NotFoundException(String request) {
        super("Count not find any resource matching this request :" + request);
    }
}