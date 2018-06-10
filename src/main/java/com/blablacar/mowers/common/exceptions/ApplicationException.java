package com.blablacar.mowers.common.exceptions;

import com.blablacar.mowers.common.ErrorDictionary;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class ApplicationException extends Exception {

    private ErrorDictionary error;

    public ApplicationException(ErrorDictionary error, String message) {
        super(message);
        this.error = error;
    }

    public ApplicationException(ErrorDictionary error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public ErrorDictionary getError() {
        return error;
    }
}
