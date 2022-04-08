package com.andy;

public class AnnotationParser implements TokenParser{
    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {
        if (TokenInputStreamHelpers.nthCharacterEquals('@', 0, inputStream)) {
            if (TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, 1, Character::isAlphabetic)
                    && TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, 1, Character::isUpperCase)) {
                int consumed = 2;

                // not sure whether digits are allowed, but I'll assume they are
                while (TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, consumed, Character::isAlphabetic)
                        ||TokenInputStreamHelpers.nthCharacterSatisfies(inputStream, consumed, Character::isDigit)) {
                    consumed++;
                }

                return new ParsedToken(TokenKind.Annotation, inputStream.makeSpan(consumed));
            }
        }
        return null;
    }
}
