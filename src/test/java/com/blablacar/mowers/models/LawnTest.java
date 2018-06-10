package com.blablacar.mowers.models;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class LawnTest {

    @Test
    public void testLawn() {
        Lawn lawn = new Lawn(1, 2);

        Assert.assertEquals(lawn.getMaxHeight(), 2);
        Assert.assertEquals(lawn.getMaxLength(), 1);
        Assert.assertNotNull(lawn.getMowers());
        Assert.assertTrue(lawn.getMowers().isEmpty());
    }

    @Test
    public void testAddMower() {
        Lawn lawn = new Lawn(1, 2);
        Mower mower = new Mower(null, null, null);

        lawn.addMower(mower);

        Assert.assertEquals(lawn.getMowers().size(), 1);

        lawn.addMower(null);
        Assert.assertEquals(lawn.getMowers().size(), 1);
    }
}
