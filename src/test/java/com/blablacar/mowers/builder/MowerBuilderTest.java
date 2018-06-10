package com.blablacar.mowers.builder;

import com.blablacar.mowers.common.MowerMove;
import com.blablacar.mowers.common.exceptions.MowerBuilderException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;
import com.blablacar.mowers.models.Position;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class MowerBuilderTest {

    private final MowerBuilderImpl mowerBuilder = new MowerBuilderImpl();

    /**
     * Success test case building mower
     */
    @Test
    public void testBuildMower() throws MowerBuilderException {
        Mower mower = mowerBuilder.buildMower("1 2 E", "LFRFL", new Lawn(5, 5));

        Assert.assertNotNull(mower);
        Assert.assertNotNull(mower.getPosition());
        Assert.assertNotNull(mower.getMoves());
        Assert.assertNotNull(mower.getLawn());
    }

    /**
     * Success test case building mower initial position
     */
    @Test
    public void testBuildMowerInitialPosition() throws MowerBuilderException {
        Position position = mowerBuilder.buildMowerInitialPosition("1 2 E");

        Assert.assertNotNull(position);
        Assert.assertEquals(position.getDirection().toString(), "E");
        Assert.assertEquals(position.getX(), 1);
        Assert.assertEquals(position.getY(), 2);
    }

    /**
     * Failure test case building mower initial position - Null data
     */
    @Test(expectedExceptions = MowerBuilderException.class)
    public void testBuildMowerInitialPositionNullData() throws MowerBuilderException {
        mowerBuilder.buildMowerInitialPosition(null);
    }

    /**
     * Failure test case building mower initial position - Too many data
     */
    @Test(expectedExceptions = MowerBuilderException.class)
    public void testBuildMowerInitialPositionTooManyData() throws MowerBuilderException {
        mowerBuilder.buildMowerInitialPosition("1 1 1 E");
    }

    /**
     * Failure test case building mower initial position - Not enough data
     */
    @Test(expectedExceptions = MowerBuilderException.class)
    public void testBuildMowerInitialPositionNotEnoughData() throws MowerBuilderException {
        mowerBuilder.buildMowerInitialPosition("1");
    }

    @DataProvider
    public Object[][] buildMowerInitialPositionWrongDataDataProvider() {
        return new Object[][]{
                {"1 2 3"},
                {"f 2 E"},
                {"1 D E"},
        };
    }

    /**
     * Failure test case building mower initial position - Wrong data - not integers
     */
    @Test(expectedExceptions = MowerBuilderException.class, dataProvider = "buildMowerInitialPositionWrongDataDataProvider")
    public void testBuildMowerInitialPositionWrongData(String data) throws MowerBuilderException {
        mowerBuilder.buildMowerInitialPosition(data);
    }


    /**
     * Success test case building mower moves
     */
    @Test
    public void testBuildMowerMoves() throws MowerBuilderException {
        String moves = "LFRFL";

        List<MowerMove> mowerMoves = mowerBuilder.buildMowerMoves(moves);

        Assert.assertNotNull(mowerMoves);
        Assert.assertEquals(mowerMoves.size(), 5);

        for (int i = 0; i < mowerMoves.size(); i++) {
            Assert.assertEquals(Character.toString(moves.charAt(i)), mowerMoves.get(i).toString());
        }
    }

    /**
     * Failure test case building mower moves - Null data
     */
    @Test(expectedExceptions = MowerBuilderException.class)
    public void testBuildMowerMovesNullData() throws MowerBuilderException {
        mowerBuilder.buildMowerMoves(null);
    }

    /**
     * Failure test case building mower moves - Empty data
     */
    @Test(expectedExceptions = MowerBuilderException.class)
    public void testBuildMowerMovesEmptyData() throws MowerBuilderException {
        mowerBuilder.buildMowerMoves("");
    }

    /**
     * Failure test case building mower moves - Incorrect data
     */
    @Test(expectedExceptions = MowerBuilderException.class)
    public void testBuildMowerMovesWrongData() throws MowerBuilderException {
        String moves = "FDSQF";
        mowerBuilder.buildMowerMoves(moves);
    }
}