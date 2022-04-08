package com.andy;

public class StringTokenInputStream extends BaseTokenInputStream {
    private String input;


    public StringTokenInputStream(String input) {
        this.input = input;
    }

    @Override
    public void consume(int length) {
        startingValue += length;
    }

    @Override
    public Character getNthCharacter(int n) {
        if (n + startingValue >= input.length()) {
            return null;
        }
        return input.charAt(n + startingValue);
    }
}
