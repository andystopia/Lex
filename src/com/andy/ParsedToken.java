package com.andy;

public class ParsedToken {
    private final TokenKind tokenKind;

    public TokenKind getKind() {
        return tokenKind;
    }

    public Span getSpan() {
        return span;
    }

    private final Span span;


    public ParsedToken(TokenKind tokenKind, Span span) {
        this.tokenKind = tokenKind;
        this.span = span;
    }

    @Override
    public String toString() {
        return "ParsedToken{" +
                "tokenKind=" + tokenKind +
                ", span=" + span +
                '}';
    }
}
