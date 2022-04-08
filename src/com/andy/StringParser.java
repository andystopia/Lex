package com.andy;

public class StringParser implements TokenParser {
    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {
        Character firstCharacter = inputStream.getNthCharacter(0);
        // if the first character is null or some character we don't
        // care about, or is null, then this is not a string token,
        // and we should just quietly return null
        if (firstCharacter == null || firstCharacter != '\"') {
            return null;
        }



        int currentParsingCharacter = 1;
        int activeBackslashCount = 0;
        while (true) {

            if (inputStream.getNthCharacter(currentParsingCharacter) == null) {
                throw new UnexpectedEndOfInput(this);
            }

            if (inputStream.getNthCharacter(currentParsingCharacter) == '\"' && activeBackslashCount % 2 == 0) {
                return new ParsedToken(TokenKind.Str, inputStream.makeSpan(currentParsingCharacter + 1));
            }

            if (inputStream.getNthCharacter(currentParsingCharacter) == '\\') {
                activeBackslashCount++;
            } else {
                activeBackslashCount = 0;
            }
            currentParsingCharacter++;
        }
    }
}
