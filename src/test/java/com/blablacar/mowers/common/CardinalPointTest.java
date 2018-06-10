package com.blablacar.mowers.common;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class CardinalPointTest {

    @Test
    public void testGetLeftCardinalPoint() {
        Assert.assertEquals(CardinalPoint.N.getLeftCardinalPoint(), CardinalPoint.W);
        Assert.assertEquals(CardinalPoint.W.getLeftCardinalPoint(), CardinalPoint.S);
        Assert.assertEquals(CardinalPoint.S.getLeftCardinalPoint(), CardinalPoint.E);
        Assert.assertEquals(CardinalPoint.E.getLeftCardinalPoint(), CardinalPoint.N);
    }

    @Test
    public void testGetRightCardinalPoint() {
        Assert.assertEquals(CardinalPoint.N.getRightCardinalPoint(), CardinalPoint.E);
        Assert.assertEquals(CardinalPoint.E.getRightCardinalPoint(), CardinalPoint.S);
        Assert.assertEquals(CardinalPoint.S.getRightCardinalPoint(), CardinalPoint.W);
        Assert.assertEquals(CardinalPoint.W.getRightCardinalPoint(), CardinalPoint.N);

    }
}