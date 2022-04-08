package com.andy;

public class Span {
    private final int lowerIndex;
    private final int upperIndex;

    public Span(int lowerIndex, int upperIndex) {
        this.lowerIndex = lowerIndex;
        this.upperIndex = upperIndex;
    }

    public int getLowerIndex() {
        return lowerIndex;
    }


    public int getUpperIndex() {
        return upperIndex;
    }

    public int getLength() {
        return upperIndex - lowerIndex;
    }
    @Override
    public String toString() {
        return "Span{" + lowerIndex +
                ".." + upperIndex +
                '}';
    }
}
