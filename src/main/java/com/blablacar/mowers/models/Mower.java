package com.blablacar.mowers.models;

import com.blablacar.mowers.common.MowerMove;
import lombok.Data;

import java.util.List;

/**
 * Brand new automatic mower able to move by itself
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
@Data
public class Mower {
    private Position position;
    private List<MowerMove> moves;
    private Lawn lawn;

    public Mower(Position position, List<MowerMove> moves, Lawn lawn) {
        this.position = position;
        this.moves = moves;
        this.lawn = lawn;
    }

    public void turnLeft() {
        position.turnLeft();
    }

    public void turnRight() {
        position.turnRight();
    }

    public void goForward() {
        position.goForward();
    }

}
