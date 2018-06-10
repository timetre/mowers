package com.blablacar.mowers.builder;

import com.blablacar.mowers.common.exceptions.LawnBuilderException;
import com.blablacar.mowers.models.Lawn;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public interface LawnBuilder {

    /**
     * Build lawn based on input file
     *
     * @param filePath
     * @return new correctly initialized Lawn
     */
    Lawn buildLawn(String filePath) throws LawnBuilderException;
}
