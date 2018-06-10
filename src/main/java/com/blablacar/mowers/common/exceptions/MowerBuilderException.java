package com.blablacar.mowers.common.exceptions;

import com.blablacar.mowers.common.ErrorDictionary;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class MowerBuilderException extends Exception {

    protected ErrorDictionary error;

    public MowerBuilderException(ErrorDictionary error, String message) {
        super(message);
        this.error = error;
    }

    public MowerBuilderException(ErrorDictionary error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public ErrorDictionary getError() {
        return error;
    }
}
