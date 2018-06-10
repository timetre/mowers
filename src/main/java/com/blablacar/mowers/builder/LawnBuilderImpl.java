package com.blablacar.mowers.builder;

import com.blablacar.mowers.common.ErrorDictionary;
import com.blablacar.mowers.common.exceptions.LawnBuilderException;
import com.blablacar.mowers.common.exceptions.MowerBuilderException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class allowing to properly build a lawn
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class LawnBuilderImpl implements LawnBuilder {

    private static Logger LOGGER = LoggerFactory.getLogger(LawnBuilderImpl.class);

    private final MowerBuilderImpl mowerBuilder = new MowerBuilderImpl();

    private static String LAWN_COORDINATES_DELIMITER = " ";

    @Override
    public Lawn buildLawn(String filePath) throws LawnBuilderException {

        Lawn lawn = null;

        try {

            //For performance issue, put file in a list
            List<String> fileElements = new ArrayList<>();
            fileElements.addAll(Files.readAllLines(Paths.get(filePath)));

            long elementsCount = fileElements.size();

            //Number of elements in list minus 1st line should be pair
            if (fileElements.isEmpty() || (fileElements.size() - 1) % 2 != 0) {
                LOGGER.error("Number of lines incorrect. Expected pair but got {}", elementsCount);
                throw new LawnBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_LAWN, "Number of lines in file incorrect");
            }

            //Get lawn dimensions - 1st element of list
            int[] lawnDimensions = getLawnDimensions(fileElements.get(0));

            LOGGER.debug("buildLawn() - Lawn dimensions : {}", lawnDimensions);

            lawn = new Lawn(lawnDimensions[0], lawnDimensions[1]);

            //Iterate over rest of file to build mowers
            int i = 1;
            while (i <= elementsCount - 1) {
                String mowerPositionAsString = fileElements.get(i);
                String mowerMovesAsString = fileElements.get(i + 1);
                i += 2;

                LOGGER.debug("buildLawn() - Mower position : {} / Mower moves : {}", mowerPositionAsString, mowerMovesAsString);

                try {
                    Mower mower = mowerBuilder.buildMower(mowerPositionAsString, mowerMovesAsString, lawn);
                    if (mower != null) {
                        lawn.addMower(mower);
                    }

                } catch (MowerBuilderException e) {
                    LOGGER.error("buildLawn({}) - Unable to build mower", filePath, e);
                    throw new LawnBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_LAWN, "Cannot read first line", e);
                }
            }

        } catch (IOException e) {
            LOGGER.error("buildLawn({}) - Unable to read file", filePath, e);
            throw new LawnBuilderException(ErrorDictionary.ERR_UNABLE_TO_BUILD_LAWN, "Cannot read first line", e);
        }

        return lawn;
    }

    protected int[] getLawnDimensions(String data) throws LawnBuilderException {
        String[] dimensionsAsString = data.split(LAWN_COORDINATES_DELIMITER);

        if (dimensionsAsString.length != 2) {
            LOGGER.error("getLawnDimensions({}) - Wrong dimensions data", data);
            throw new LawnBuilderException(ErrorDictionary.ERR_INCORRECT_DATA, "Dimensions should be composed of right coordinates (x, y)");
        }

        try {
            return Arrays.stream(dimensionsAsString).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException ex) {
            LOGGER.error("getLawnDimensions({}) - Cannot convert dimensions to int", data);
            throw new LawnBuilderException(ErrorDictionary.ERR_INCORRECT_DATA, "Cannot convert dimensions to int");
        }
    }

}
