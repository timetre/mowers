package com.blablacar.mowers.models;

import com.blablacar.mowers.common.CardinalPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class MowerTest {

    @DataProvider
    public Object[][] turnLeftDataProvider() {
        return new Object[][]{
                //Mower, expected left direction
                {new Mower(new Position(1, 2, CardinalPoint.N), null, null), CardinalPoint.W},
                {new Mower(new Position(1, 2, CardinalPoint.W), null, null), CardinalPoint.S},
                {new Mower(new Position(1, 2, CardinalPoint.S), null, null), CardinalPoint.E},
                {new Mower(new Position(1, 2, CardinalPoint.E), null, null), CardinalPoint.N},
        };
    }

    @Test(dataProvider = "turnLeftDataProvider")
    public void testTurnLeft(Mower mower, CardinalPoint expectedDirection) {
        mower.turnLeft();
        Assert.assertEquals(mower.getPosition().getDirection(), expectedDirection);
    }

    @DataProvider
    public Object[][] turnRightDataProvider() {
        return new Object[][]{
                //Mower, expected right direction
                {new Mower(new Position(1, 2, CardinalPoint.N), null, null), CardinalPoint.E},
                {new Mower(new Position(1, 2, CardinalPoint.E), null, null), CardinalPoint.S},
                {new Mower(new Position(1, 2, CardinalPoint.S), null, null), CardinalPoint.W},
                {new Mower(new Position(1, 2, CardinalPoint.W), null, null), CardinalPoint.N},
        };
    }

    @Test(dataProvider = "turnRightDataProvider")
    public void testTurnRight(Mower mower, CardinalPoint expectedDirection) {
        mower.turnRight();
        Assert.assertEquals(mower.getPosition().getDirection(), expectedDirection);
    }

    @DataProvider
    public Object[][] goForwardDataProvider() {
        return new Object[][]{
                //Mower, expected right direction
                {new Mower(new Position(1, 1, CardinalPoint.N), null, null), new Position(1, 2, CardinalPoint.N)},
                {new Mower(new Position(1, 1, CardinalPoint.E), null, null), new Position(2, 1, CardinalPoint.E)},
                {new Mower(new Position(1, 1, CardinalPoint.S), null, null), new Position(1, 0, CardinalPoint.S)},
                {new Mower(new Position(1, 1, CardinalPoint.W), null, null), new Position(0, 1, CardinalPoint.W)},
        };
    }

    @Test(dataProvider = "goForwardDataProvider")
    public void testGoForward(Mower mower, Position expectedPosition) {
        mower.goForward();
        Assert.assertEquals(mower.getPosition(), expectedPosition);
    }
}
