package com.andy;

public abstract class BaseTokenInputStream implements MutableTokenInputStream {
    protected int startingValue = 0;

    @Override
    public Span makeSpan(int consumed) {
        return new Span(startingValue, startingValue + consumed);
    }
}
