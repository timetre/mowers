package com.blablacar.mowers;

import com.blablacar.mowers.builder.LawnBuilder;
import com.blablacar.mowers.common.ErrorDictionary;
import com.blablacar.mowers.common.MainModule;
import com.blablacar.mowers.common.exceptions.ApplicationException;
import com.blablacar.mowers.common.exceptions.LawnBuilderException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;
import com.blablacar.mowers.models.Position;
import com.blablacar.mowers.services.MowerService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class Runner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    private static final int SUCCESS_CODE = 200;

    //Services used to run build lawn and mowers
    private static MowerService mowerService;
    private static LawnBuilder lawnBuilder;

    /**
     * Main method - Program entry point
     *
     * @param args
     */
    public static void main(String[] args) {

        //Must have only one input arg (file path to process)
        //Otherwise, log error and return exit error code

        ErrorDictionary error = checkInputArgs(args);

        if (error != null) {
            LOGGER.error("main({}) - {}", args, error.getFullDescription());
            System.exit(error.getCode());
        }
        //Keep going

        try {

            initServices();

            execute(args);

            System.exit(SUCCESS_CODE);

        } catch (Exception ex) {
            LOGGER.error("main({}) - {}", args, ErrorDictionary.ERR_UNEXPECTED_ERROR.getFullDescription(), ex);
            System.exit(ErrorDictionary.ERR_UNEXPECTED_ERROR.getCode());
        }
    }

    /**
     * Checking if input args exists and is a readable file
     *
     * @param args
     * @return error dictionary
     */
    protected static ErrorDictionary checkInputArgs(String[] args) {
        if (args == null || args.length != 1) {
            return ErrorDictionary.ERR_WRONG_INPUT_PARAMS;
        }

        File file = new File(args[0]);
        if (!file.exists() && !file.isFile()) {
            return ErrorDictionary.ERR_CANNOT_READ_FILE;
        }

        return null;
    }

    /**
     * Initializing services using Guice injection
     */
    private static void initServices() {
        Injector injector = Guice.createInjector(new MainModule());
        mowerService = injector.getInstance(MowerService.class);
        lawnBuilder = injector.getInstance(LawnBuilder.class);
    }

    /**
     * Execute method supposed to launch fleet of mowers
     *
     * @param args
     */
    protected static void execute(String[] args) throws LawnBuilderException, ApplicationException {

        //File consistency should have been made before.
        //No need for checking it in here
        String filePath = args[0];

        Lawn lawn = lawnBuilder.buildLawn(filePath);

        if (lawn != null) {

            for (Mower mower : lawn.getMowers()) {
                LOGGER.info("------ Moving Mower located at {} -----", mower.getPosition());

                Position position = mowerService.moveMower(mower);

                LOGGER.info("Final mower position {}", position);
            }

        }

    }

}
