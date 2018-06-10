package com.blablacar.mowers.services;

import com.blablacar.mowers.common.exceptions.ApplicationException;
import com.blablacar.mowers.models.Mower;
import com.blablacar.mowers.models.Position;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public interface MowerService {

    /**
     * Move method for mower
     * Checks if next position is allowed before performing the move
     *
     * @param mower
     * @return last position of the mower
     */
    Position moveMower(Mower mower) throws ApplicationException;


}
