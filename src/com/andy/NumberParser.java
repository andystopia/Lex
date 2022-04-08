package com.andy;

import java.util.function.Predicate;

public class NumberParser implements TokenParser{
    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {
        // okay, so let's see if I can pull this off.

        // a digit followed by not a digit.
        // handles the single digit case.
        if (TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, 0, Character::isDigit) && TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, 1, Predicate.not(Character::isDigit))) {
            return new ParsedToken(TokenKind.Numeric, inputStream.makeSpan(1));
        }
        else if (TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, 0, Character::isDigit)) {
            if (TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, 1, Character::isDigit) ||
            TokenInputStreamHelpers.nthCharacterEquals('b', 1, inputStream)
            || TokenInputStreamHelpers.nthCharacterEquals('x', 1, inputStream)) {
                int consumed = 2;

                while (TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, consumed, Character::isDigit)) {
                    consumed++;
                }

                if (consumed != 2) {
                    return new ParsedToken(TokenKind.Numeric, inputStream.makeSpan(consumed));
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
