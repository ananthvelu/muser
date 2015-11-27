package com.reviewboard.user.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Old and New Password Cannot be same
 */
@ResponseStatus(value= HttpStatus.CONFLICT)
public class OldAndNewPaswordCannotBeSame extends RuntimeException {
    /**
     * Unique ID for Serialized object
     */
    private static final long serialVersionUID = -1790211652911971725L;

    public OldAndNewPaswordCannotBeSame() {
        super("Old and new passwords cannot be same. Try entering different password");
    }
}
