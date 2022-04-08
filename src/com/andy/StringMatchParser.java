package com.andy;

public class StringMatchParser implements TokenParser{
    private final String pattern;
    private final TokenKind kind;

    public StringMatchParser(String pattern, TokenKind kind) {
        this.pattern = pattern;
        this.kind = kind;
    }


    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {
        int n = 0;
        while (inputStream.getNthCharacter(n) != null && n < pattern.length()) {
            char c = inputStream.getNthCharacter(n);

            if (c != pattern.charAt(n)) {
                return null;
            }
            n++;
        }

        if (inputStream.getNthCharacter(n) == null && n < pattern.length()) {
            return null;
        }

        return new ParsedToken(kind, inputStream.makeSpan(pattern.length()));
    }
}
