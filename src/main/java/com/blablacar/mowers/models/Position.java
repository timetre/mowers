package com.blablacar.mowers.models;

import com.blablacar.mowers.common.CardinalPoint;
import lombok.Data;

/**
 * Position for the mower
 * Composed of x and y position on the lawn grid plus mowers cardinal direction
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
@Data
public class Position {

    private int x;
    private int y;
    private CardinalPoint direction;

    /**
     * Initialize mower's position
     *
     * @param x
     * @param y
     * @param direction
     */
    public Position(int x, int y, CardinalPoint direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.getLeftCardinalPoint();
    }

    public void turnRight() {
        direction = direction.getRightCardinalPoint();
    }

    public void goForward() {

        switch (direction) {
            case E:
                x += 1;
                break;
            case W:
                x -= 1;
                break;
            case N:
                y += 1;
                break;
            case S:
                y -= 1;
                break;
        }
    }


    @Override
    public String toString() {
        return x + " " + y + " " + direction;
    }
}
