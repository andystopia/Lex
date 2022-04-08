package com.andy;

public class UnexpectedEndOfInput extends ParsingError {
    private final TokenParser parser;
    public UnexpectedEndOfInput(TokenParser parser) {
        this.parser = parser;
    }
}
