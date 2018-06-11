package com.blablacar.mowers.services;

import com.blablacar.mowers.common.CardinalPoint;
import com.blablacar.mowers.common.MowerMove;
import com.blablacar.mowers.common.exceptions.ApplicationException;
import com.blablacar.mowers.models.Lawn;
import com.blablacar.mowers.models.Mower;
import com.blablacar.mowers.models.Position;
import lombok.Data;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public class LawnServiceTest {

    private MowerServiceImpl mowerService = new MowerServiceImpl();
    private LawnServiceImpl lawnService = new LawnServiceImpl(mowerService);

    @DataProvider
    public Object[][] mowLawnDataProvider() {

        Lawn lawn = new Lawn(5, 5);
        Position initialPosition = new Position(1, 2, CardinalPoint.N);
        List<MowerMove> mowerMoves = Arrays.asList(MowerMove.L, MowerMove.F, MowerMove.L, MowerMove.F, MowerMove.L, MowerMove.F, MowerMove.L, MowerMove.F, MowerMove.F);
        lawn.addMower(new Mower(initialPosition, mowerMoves, lawn));

        Lawn neighborLawn = new Lawn(5, 5);
        Position initialPositionNeighborMower = new Position(3, 3, CardinalPoint.E);
        List<MowerMove> mowerMovesNeighbor = Arrays.asList(MowerMove.F, MowerMove.F, MowerMove.R, MowerMove.F, MowerMove.F, MowerMove.R, MowerMove.F, MowerMove.R, MowerMove.R, MowerMove.F);
        neighborLawn.addMower(new Mower(initialPositionNeighborMower, mowerMovesNeighbor, neighborLawn));

        return new Object[][] {
                // Lawn ; Expected final Position
                {lawn, new Position(1, 3, CardinalPoint.N)},
                {neighborLawn, new Position(5, 1, CardinalPoint.E)}
        };

    }

    @Test(dataProvider = "mowLawnDataProvider")
    public void testMowLawn(Lawn lawn, Position expectedFinalPosition) throws ApplicationException {

        Assert.assertNotNull(lawn.getMowers());
        Assert.assertEquals(lawn.getMowers().size(), 1);


        boolean result = lawnService.mowLawn(lawn);

        Mower mower = lawn.getMowers().get(0);
        Position finalPosition = mower.getPosition();


        Assert.assertEquals(result, true);
        Assert.assertEquals(finalPosition, expectedFinalPosition);
    }

    @Test(expectedExceptions = ApplicationException.class)
    public void testMowLawnNull() throws ApplicationException {
        lawnService.mowLawn(null);
    }
}
