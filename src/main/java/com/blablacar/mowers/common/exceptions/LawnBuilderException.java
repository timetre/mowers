package com.blablacar.mowers.common.exceptions;

import com.blablacar.mowers.common.ErrorDictionary;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class LawnBuilderException extends Exception {

    private ErrorDictionary error;

    public LawnBuilderException(ErrorDictionary error, String message) {
        super(message);
        this.error = error;
    }

    public LawnBuilderException(ErrorDictionary error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public ErrorDictionary getError() {
        return error;
    }
}
