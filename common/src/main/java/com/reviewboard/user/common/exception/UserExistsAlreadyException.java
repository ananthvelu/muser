package com.reviewboard.user.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User exists already with the given username
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class UserExistsAlreadyException extends RuntimeException {
    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = -1790211652911971724L;

    public UserExistsAlreadyException(String request) {
        super("Username already existing. Try with different username:" + request);
    }
}