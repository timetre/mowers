package com.blablacar.mowers.builder;

import com.blablacar.mowers.common.exceptions.LawnBuilderException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.stream.Collectors;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class LawnBuilderTest {

    /**
     * Success test case of lawn builder
     */
    @Test
    public void testBuildLawn() throws LawnBuilderException {
        LawnBuilderImpl lawnBuilder = new LawnBuilderImpl();

        Lawn lawn = lawnBuilder.buildLawn(getClass().getClassLoader().getResource("test-input-lawn-builder").getPath());

        Assert.assertNotNull(lawn);
        Assert.assertEquals(lawn.getMaxHeight(), 5);
        Assert.assertEquals(lawn.getMaxLength(), 6);
        Assert.assertNotNull(lawn.getMowers());
        Assert.assertEquals(lawn.getMowers().size(), 2);

        Mower firstMower = lawn.getMowers().get(0);
        Mower secondMower = lawn.getMowers().get(1);

        Assert.assertEquals(firstMower.getPosition().toString(), "1 2 N");
        Assert.assertEquals(secondMower.getPosition().toString(), "3 3 E");

        Assert.assertEquals(firstMower.getMoves().stream().map(Object::toString).collect(Collectors.joining("")), "LFLFLFLFF");
        Assert.assertEquals(secondMower.getMoves().stream().map(Object::toString).collect(Collectors.joining("")), "FFRFFRFRRF");
    }

    /**
     * Failure test case of lawn builder - File not found
     */
    @Test(expectedExceptions = LawnBuilderException.class)
    public void testBuildLawnNoFile() throws LawnBuilderException {
        LawnBuilderImpl lawnBuilder = new LawnBuilderImpl();
        lawnBuilder.buildLawn("no-file");
    }

    /**
     * Failure test case of lawn builder - Incorrect number of lines cannot build lawn
     */
    @Test(expectedExceptions = LawnBuilderException.class)
    public void testBuildLawnMissingData() throws LawnBuilderException {
        LawnBuilderImpl lawnBuilder = new LawnBuilderImpl();
        lawnBuilder.buildLawn(getClass().getClassLoader().getResource("ko-lawn-builder").getPath());
    }

    /**
     * Success test case trying to compute lawn dimensions
     */
    @Test
    public void testGetLawnDimensions() throws LawnBuilderException {
        LawnBuilderImpl lawnBuilder = new LawnBuilderImpl();
        int[] dimensions = lawnBuilder.getLawnDimensions("3 5");

        Assert.assertEquals(dimensions.length, 2);
        Assert.assertEquals(dimensions[0], 3);
        Assert.assertEquals(dimensions[1], 5);
    }

    /**
     * Failure test case trying to compute lawn dimensions - Not numbers
     */
    @Test(expectedExceptions = LawnBuilderException.class)
    public void testGetLawnDimensionsException() throws LawnBuilderException {
        LawnBuilderImpl lawnBuilder = new LawnBuilderImpl();
        lawnBuilder.getLawnDimensions("v f");
    }

    /**
     * Failure test case trying to compute lawn dimensions - Too many values
     */
    @Test(expectedExceptions = LawnBuilderException.class)
    public void testGetLawnDimensionsTooManyData() throws LawnBuilderException {
        LawnBuilderImpl lawnBuilder = new LawnBuilderImpl();
        lawnBuilder.getLawnDimensions("1 2 3");
    }

    /**
     * Failure test case trying to compute lawn dimensions - Not enough values
     */
    @Test(expectedExceptions = LawnBuilderException.class)
    public void testGetLawnDimensionsNotEnoughData() throws LawnBuilderException {
        LawnBuilderImpl lawnBuilder = new LawnBuilderImpl();
        lawnBuilder.getLawnDimensions("1");
    }

    /**
     * Failure test case trying to compute lawn dimensions - wrong characters
     */
    @Test(expectedExceptions = LawnBuilderException.class)
    public void testGetLawnDimensionsWrongValues() throws LawnBuilderException {
        LawnBuilderImpl lawnBuilder = new LawnBuilderImpl();
        lawnBuilder.getLawnDimensions("1, 2");
    }
}