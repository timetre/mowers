package com.blablacar.mowers.services;

import com.blablacar.mowers.common.exceptions.ApplicationException;
import com.blablacar.mowers.models.Lawn;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public interface LawnService {

    /**
     * Method to invoke when trying to start the mowers and wanting to mow the lawn
     *
     * @param lawn
     * @return true when success false otherwise
     */
    boolean mowLawn(Lawn lawn) throws ApplicationException;
}
