package com.blablacar.mowers.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * The lawn being mowed
 *
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
@Data
public class Lawn {

    private List<Mower> mowers;
    private int maxHeight;
    private int maxLength;

    public Lawn(int x, int y) {
        maxHeight = y;
        maxLength = x;
        mowers = new ArrayList<>();
    }

    public void addMower(Mower mower) {
        if (mower != null) {
            mowers.add(mower);
        }
    }
}
