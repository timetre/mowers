package com.blablacar.mowers.builder;

import com.blablacar.mowers.common.exceptions.MowerBuilderException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public interface MowerBuilder {
    Mower buildMower(String mowerPositionsFromFile, String mowerMovesFromFile, Lawn lawn) throws MowerBuilderException;
}
