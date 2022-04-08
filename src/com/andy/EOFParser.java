package com.andy;

public class EOFParser implements TokenParser{
    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {
        if (inputStream.getNthCharacter(0) == null) {
            return new ParsedToken(TokenKind.EOF, inputStream.makeSpan(0));
        }
        return null;
    }
}
