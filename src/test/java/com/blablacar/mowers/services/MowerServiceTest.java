package com.blablacar.mowers.services;

import com.blablacar.mowers.common.CardinalPoint;
import com.blablacar.mowers.common.MowerMove;
import com.blablacar.mowers.common.exceptions.ApplicationException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;
import com.blablacar.mowers.models.Position;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class MowerServiceTest {

    private MowerService mowerService = new MowerService();

    @DataProvider
    public Object[][] isAllowedToMoveDataProvider() {
        Lawn lawn = new Lawn(5, 5);
        return new Object[][]{
                //Mower ; Allowed to move
                {new Mower(new Position(0, 0, CardinalPoint.S), Arrays.asList(MowerMove.F), lawn), false},
                {new Mower(new Position(0, 0, CardinalPoint.N), Arrays.asList(MowerMove.F), lawn), true},
                {new Mower(new Position(0, 0, CardinalPoint.W), Arrays.asList(MowerMove.F), lawn), false},
                {new Mower(new Position(0, 0, CardinalPoint.E), Arrays.asList(MowerMove.F), lawn), true},
                {new Mower(new Position(2, 2, CardinalPoint.N), Arrays.asList(MowerMove.F), lawn), true},
                {new Mower(new Position(2, 2, CardinalPoint.S), Arrays.asList(MowerMove.F), lawn), true},
                {new Mower(new Position(2, 2, CardinalPoint.W), Arrays.asList(MowerMove.F), lawn), true},
                {new Mower(new Position(2, 2, CardinalPoint.E), Arrays.asList(MowerMove.F), lawn), true},
                {new Mower(new Position(5, 5, CardinalPoint.N), Arrays.asList(MowerMove.F), lawn), false},
                {new Mower(new Position(5, 5, CardinalPoint.S), Arrays.asList(MowerMove.F), lawn), true},
                {new Mower(new Position(5, 5, CardinalPoint.W), Arrays.asList(MowerMove.F), lawn), true},
                {new Mower(new Position(5, 5, CardinalPoint.E), Arrays.asList(MowerMove.F), lawn), false},

        };
    }

    @Test(dataProvider = "isAllowedToMoveDataProvider")
    public void testIsAllowedToMove(Mower mower, boolean expectedAllowedToMove) throws ApplicationException {

        boolean allowedToMove = mowerService.isAllowedToMove(mower);

        Assert.assertEquals(allowedToMove, expectedAllowedToMove);

    }

    @Test(expectedExceptions = ApplicationException.class)
    public void testIsAllowedToMoveNullMower() throws ApplicationException {
        mowerService.isAllowedToMove(null);
    }

    @Test(expectedExceptions = ApplicationException.class)
    public void testIsAllowedToMoveNullPosition() throws ApplicationException {
        mowerService.isAllowedToMove(new Mower(null, Arrays.asList(MowerMove.F), new Lawn(5, 5)));
    }

    @Test(expectedExceptions = ApplicationException.class)
    public void testIsAllowedToMoveNullCardinal() throws ApplicationException {
        mowerService.isAllowedToMove(new Mower(new Position(5, 5, null), Arrays.asList(MowerMove.F), new Lawn(5, 5)));
    }

    @DataProvider
    public Object[][] moveMowerDataProvider() {
        Lawn lawn = new Lawn(5, 5);

        return new Object[][] {
                //Mower ; expected new position
                {new Mower(new Position(0, 0, CardinalPoint.N), Arrays.asList(MowerMove.F), lawn), new Position(0, 1, CardinalPoint.N)},
                {new Mower(new Position(0, 0, CardinalPoint.E), Arrays.asList(MowerMove.F), lawn), new Position(1, 0, CardinalPoint.E)},
                //Same position expected as move is forbidden (out of lawn)
                {new Mower(new Position(0, 0, CardinalPoint.S), Arrays.asList(MowerMove.F), lawn), new Position(0, 0, CardinalPoint.S)},
                {new Mower(new Position(0, 0, CardinalPoint.W), Arrays.asList(MowerMove.F), lawn), new Position(0, 0, CardinalPoint.W)},
        };
    }

    @Test(dataProvider = "moveMowerDataProvider")
    public void testMoveMower(Mower mower, Position expectedPosition) throws ApplicationException {

        Position position = mowerService.moveMower(mower);

        Assert.assertEquals(position, expectedPosition);
    }

    @Test(expectedExceptions = ApplicationException.class)
    public void testMoveMowerNull() throws ApplicationException {
        mowerService.moveMower(null);
    }
}
