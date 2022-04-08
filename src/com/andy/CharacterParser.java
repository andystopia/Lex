package com.andy;

public class CharacterParser implements TokenParser{
    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {
        // ugly
        if (TokenInputStreamHelpers.nthCharacterEquals('\'', 0, inputStream)) {
            if (TokenInputStreamHelpers.nthCharacterEquals('\\', 1, inputStream)) {
                // now we have to allow a few things.
                // we won't deal with unicode scalars for now.
                if (TokenInputStreamHelpers.nthCharacterAnyOf(inputStream, 2, new char[]{'n', 'r', 't', '\'', '"', '\\'}))
                {
                    if (TokenInputStreamHelpers.nthCharacterEquals('\'', 3, inputStream)) {
                        return new ParsedToken(TokenKind.Character, inputStream.makeSpan(4));
                    }
                }
            } else {
                if (TokenInputStreamHelpers.nthCharacterEquals('\'', 2, inputStream)) {
                    return new ParsedToken(TokenKind.Character, inputStream.makeSpan(3));
                }
            }
        }
        return null;
    }
}
