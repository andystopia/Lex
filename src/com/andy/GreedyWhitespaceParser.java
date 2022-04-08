package com.andy;

import java.util.List;

/**
 * this class greedily parses whitespace.
 *
 * it will combine any length of whitespace
 * tokens down to one whitespace token.
 */
public class GreedyWhitespaceParser implements TokenParser{

    private List<Character> whitespaceCharacters =  List.of(' ', '\r', '\n', '\t');
    public List<Character> getWhitespaceCharacters() {
        return whitespaceCharacters;
    }

    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {

        if (inputStream.getNthCharacter(0) == null) {
            return null;
        }

        int consumedCharacters = 0;
        Character firstCharacter;
        do {
            firstCharacter = inputStream.getNthCharacter(consumedCharacters);
            if (firstCharacter != null) {
                Character finalFirstCharacter = firstCharacter;
                if (getWhitespaceCharacters().stream().noneMatch(o -> o.equals(finalFirstCharacter))) {
                    break;
                }
            }
            consumedCharacters += 1;
        } while (firstCharacter != null);

        if (consumedCharacters == 0) {
            return null;
        }
        return new ParsedToken(TokenKind.Whitespace, inputStream.makeSpan(consumedCharacters));
    }
}
