package com.blablacar.mowers.builder;

import com.blablacar.mowers.common.CardinalPoint;
import com.blablacar.mowers.common.ErrorDictionary;
import com.blablacar.mowers.common.MowerMove;
import com.blablacar.mowers.common.exceptions.MowerBuilderException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;
import com.blablacar.mowers.models.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class allowing to properly build a mower
 *
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class MowerBuilderImpl implements MowerBuilder {

    private static Logger LOGGER = LoggerFactory.getLogger(MowerBuilderImpl.class);

    private static String MOWER_POSITION_DELIMITER = " ";

    @Override
    public Mower buildMower(String mowerPositionsFromFile, String mowerMovesFromFile, Lawn lawn) throws MowerBuilderException {

        Position position = buildMowerInitialPosition(mowerPositionsFromFile);
        List<MowerMove> mowerMoves = buildMowerMoves(mowerMovesFromFile);

        return new Mower(position, mowerMoves, lawn);
    }

    /**
     * Build a Position object which represents the initial position of the mower in the lawn
     * Input should be composed of 2 integers and 1 CardinalPoint letter (N-E-S-W)
     * All of them separated by a space eg : 1 2 E or 5 3 N
     *
     * @param mowerPositionsAsString
     * @return
     * @throws MowerBuilderException
     */
    protected Position buildMowerInitialPosition(String mowerPositionsAsString) throws MowerBuilderException {

        if (mowerPositionsAsString == null || mowerPositionsAsString.isEmpty()) {
            LOGGER.error("buildMowerInitialPosition({}) - Cannot build position due to incorrect input values", mowerPositionsAsString);
            throw new MowerBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_MOWER, "Cannot build position due to incorrect input values");
        }

        String[] mowerPositionsArray = mowerPositionsAsString.split(MOWER_POSITION_DELIMITER);

        if (mowerPositionsArray == null || mowerPositionsArray.length != 3) {
            LOGGER.error("buildMowerInitialPosition({}) - Cannot build position due to incorrect input values", mowerPositionsArray);
            throw new MowerBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_MOWER, "Cannot build position due to incorrect input values");
        }

        try {
            int x = Integer.valueOf(mowerPositionsArray[0]);
            int y = Integer.valueOf(mowerPositionsArray[1]);
            CardinalPoint cardinalPoint = CardinalPoint.valueOf(mowerPositionsArray[2]);

            Object[] parameters = {x, y, cardinalPoint};
            LOGGER.debug("buildMowerInitialPosition() - x={}, y={}, cardinalPoint={}", parameters);

            return new Position(x, y, cardinalPoint);

        } catch (IllegalArgumentException e) {
            LOGGER.error("buildMowerInitialPosition({}) - Cannot build position", mowerPositionsArray);
            throw new MowerBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_MOWER, "Cannot build position");
        }
    }

    /**
     * Build a List of MowerMove based on mower moves as string (composed of R L or F)
     * List should be formatted with no separator eg : LRFFRL
     *
     * @param mowerMovesAsString
     * @return
     * @throws MowerBuilderException
     */
    protected List<MowerMove> buildMowerMoves(String mowerMovesAsString) throws MowerBuilderException {
        if (mowerMovesAsString == null || mowerMovesAsString.isEmpty()) {
            LOGGER.error("buildMowerMoves({}) - Cannot build moves due to incorrect input values", mowerMovesAsString);
            throw new MowerBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_MOWER, "Cannot build position due to incorrect input values");
        }

        List<MowerMove> mowerMoves = new ArrayList<>();

        try {
            for (int i = 0; i < mowerMovesAsString.length(); ++i) {
                String mowerMoveAsString = String.valueOf(mowerMovesAsString.charAt(i));

                try {
                    MowerMove mowerMove = MowerMove.valueOf(mowerMoveAsString);
                    mowerMoves.add(mowerMove);
                    LOGGER.debug("buildMowerMoves() - mowerMove={}", mowerMove);
                } catch (IllegalArgumentException e) {
                    LOGGER.error("buildMowerMoves({}) - Cannot build mower move", mowerMoveAsString);
                    throw new MowerBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_MOWER, "Cannot build mower move");
                }
            }

            return mowerMoves;

        } catch (IllegalArgumentException e) {
            LOGGER.error("buildMowerMoves({}) - Cannot build mower move", mowerMovesAsString);
            throw new MowerBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_MOWER, "Cannot build position");
        }
    }
}
