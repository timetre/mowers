package com.blablacar.mowers.services;

import com.blablacar.mowers.builder.LawnBuilderImpl;
import com.blablacar.mowers.common.ErrorDictionary;
import com.blablacar.mowers.common.exceptions.ApplicationException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;
import com.blablacar.mowers.models.Position;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class LawnServiceImpl implements LawnService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LawnBuilderImpl.class);

    private final MowerService mowerService;

    @Inject
    public LawnServiceImpl(MowerService mowerService) {
        this.mowerService = mowerService;
    }

    @Override
    public boolean mowLawn(Lawn lawn) throws ApplicationException {

        if (lawn == null) {
            throw new ApplicationException(ErrorDictionary.ERR_UNEXPECTED_ERROR, "Lawn cannot be null");
        }


        for (Mower mower : lawn.getMowers()) {
            LOGGER.info("------ Moving Mower located at {} -----", mower.getPosition());

            Position position = mowerService.moveMower(mower);

            LOGGER.info("Final mower position {}", position);
        }

        //Everything went well - return true
        return true;
    }
}
