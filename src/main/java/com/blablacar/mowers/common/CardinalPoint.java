package com.blablacar.mowers.common;

/**
 * Every cardinal points used by the application for the mower's orientation
 *
 * @author Jeremy Vincent <j.vincent@meetic-corp.com>
 */
public enum CardinalPoint {
    N {
        @Override
        public CardinalPoint getLeftCardinalPoint() {
            return CardinalPoint.W;
        }

        @Override
        public CardinalPoint getRightCardinalPoint() {
            return CardinalPoint.E;
        }
    },
    S {
        @Override
        public CardinalPoint getLeftCardinalPoint() {
            return CardinalPoint.E;
        }

        @Override
        public CardinalPoint getRightCardinalPoint() {
            return CardinalPoint.W;
        }
    },
    W {
        @Override
        public CardinalPoint getLeftCardinalPoint() {
            return CardinalPoint.S;
        }

        @Override
        public CardinalPoint getRightCardinalPoint() {
            return CardinalPoint.N;
        }
    },
    E {
        @Override
        public CardinalPoint getLeftCardinalPoint() {
            return CardinalPoint.N;
        }

        @Override
        public CardinalPoint getRightCardinalPoint() {
            return CardinalPoint.S;
        }
    };


    //Used to get left position of the current
    public abstract CardinalPoint getLeftCardinalPoint();

    //Used to get right position of the current
    public abstract CardinalPoint getRightCardinalPoint();
}
