package com.andy;

public class IdentifierParser implements TokenParser {

    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {

        // the rules behind what makes a valid
        // lexical token are quite simple
        // no preceding numbers and then the
        // rest is up to you, just split on the
        // whitespace.

        if (TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, 0, Character::isAlphabetic)) {
            int consumed = 1;
            while (TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, consumed, Character::isAlphabetic) || TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, consumed, Character::isDigit)) {
                consumed++;
            }
            return new ParsedToken(TokenKind.Ident, inputStream.makeSpan(consumed));
        }
        return null;
    }
}
