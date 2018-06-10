package com.blablacar.mowers.services;

import com.blablacar.mowers.common.ErrorDictionary;
import com.blablacar.mowers.common.MowerMove;
import com.blablacar.mowers.common.exceptions.ApplicationException;
import com.blablacar.mowers.models.Mower;
import com.blablacar.mowers.models.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service allowing mower to move and cut all the grass
 *
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class MowerService {

    private static Logger LOGGER = LoggerFactory.getLogger(MowerService.class);

    /**
     * Move method for mower
     * Checks if next position is allowed before performing the move
     *
     * @param mower
     * @return last position of the mower
     */
    public Position moveMower(Mower mower) throws ApplicationException {

        if (mower == null) {
            throw new ApplicationException(ErrorDictionary.ERR_UNEXPECTED_ERROR, "Mower cannot be null");
        }

        for (MowerMove mowerMove : mower.getMoves()) {

            switch (mowerMove) {
                case F:
                    //Before moving, mower must check if its next position is not out of lawn
                    if (isAllowedToMove(mower)) {
                        LOGGER.debug("Going forward");
                        mower.goForward();
                    } else {
                        LOGGER.debug("Not allowed to go out of lawn. You'll get caught by the law enforcements");
                    }
                    break;

                case L:
                    //Can always change its direction
                    LOGGER.debug("Going left");
                    mower.turnLeft();
                    break;

                case R:
                    //Can always change its direction
                    LOGGER.debug("Going right");
                    mower.turnRight();
                    break;
            }
        }

        return mower.getPosition();
    }

    /**
     * Is the mower allowed to move to next position
     *
     * @param mower
     * @return true if allowed. false otherwise
     */
    protected boolean isAllowedToMove(Mower mower) throws ApplicationException {
        if (mower == null ||
                (mower != null && mower.getPosition() == null) ||
                (mower != null && mower.getPosition() != null && mower.getPosition().getDirection() == null)) {
            LOGGER.error("isAllowedToMove({}) - Mower and its directions cannot be null", mower);
            throw new ApplicationException(ErrorDictionary.ERR_UNEXPECTED_ERROR, "Mower and its directions cannot be null");
        }
        //Computing next position to move to
        Position nextPosition = new Position(mower.getPosition().getX(), mower.getPosition().getY(), mower.getPosition().getDirection());
        //Try to reach position
        nextPosition.goForward();

        //Check if trying to cross boundaries
        return nextPosition.getX() >= 0 && nextPosition.getX() <= mower.getLawn().getMaxLength() && nextPosition.getY() >= 0 && nextPosition.getY() <= mower.getLawn().getMaxHeight();
    }

}
