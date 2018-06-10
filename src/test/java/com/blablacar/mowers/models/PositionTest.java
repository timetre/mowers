package com.blablacar.mowers.models;

import com.blablacar.mowers.common.CardinalPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class PositionTest {

    @DataProvider
    public Object[][] turnLeftDataProvider() {
        return new Object[][]{
                //Initial cardinal point ; expected cardinal point
                {CardinalPoint.N, CardinalPoint.W},
                {CardinalPoint.W, CardinalPoint.S},
                {CardinalPoint.S, CardinalPoint.E},
                {CardinalPoint.E, CardinalPoint.N},
        };
    }

    @Test(dataProvider = "turnLeftDataProvider")
    public void testTurnLeft(CardinalPoint origin, CardinalPoint expected) {

        Position position = new Position(1, 2, origin);
        position.turnLeft();

        Assert.assertEquals(position.getDirection(), expected);
    }

    @DataProvider
    public Object[][] turnRightDataProvider() {
        return new Object[][]{
                //Initial cardinal point ; expected cardinal point
                {CardinalPoint.N, CardinalPoint.E},
                {CardinalPoint.E, CardinalPoint.S},
                {CardinalPoint.S, CardinalPoint.W},
                {CardinalPoint.W, CardinalPoint.N},
        };
    }

    @Test(dataProvider = "turnRightDataProvider")
    public void testTurnRight(CardinalPoint origin, CardinalPoint expected) {

        Position position = new Position(1, 2, origin);
        position.turnRight();

        Assert.assertEquals(position.getDirection(), expected);

    }

    @DataProvider
    public Object[][] goForwardDataProvider() {
        return new Object[][]{
                //Initial cardinal point ; expected cardinal point
                {new Position(1, 1, CardinalPoint.N), new Position(1, 2, CardinalPoint.N)},
                {new Position(1, 1, CardinalPoint.E), new Position(2, 1, CardinalPoint.E)},
                {new Position(1, 1, CardinalPoint.S), new Position(1, 0, CardinalPoint.S)},
                {new Position(1, 1, CardinalPoint.W), new Position(0, 1, CardinalPoint.W)},
        };
    }

    @Test(dataProvider = "goForwardDataProvider")
    public void testGoForward(Position origin, Position expected) {

        origin.goForward();

        Assert.assertEquals(origin, expected);
    }
}
