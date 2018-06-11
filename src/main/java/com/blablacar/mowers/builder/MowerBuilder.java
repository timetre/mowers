package com.blablacar.mowers.builder;

import com.blablacar.mowers.common.exceptions.MowerBuilderException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public interface MowerBuilder {

    /**
     * Build a mower based on its position as string and moves as string
     *
     * @param mowerPositionsFromFile
     * @param mowerMovesFromFile
     * @param lawn
     * @return
     * @throws MowerBuilderException
     */
    Mower buildMower(String mowerPositionsFromFile, String mowerMovesFromFile, Lawn lawn) throws MowerBuilderException;
}
