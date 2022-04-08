package com.andy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tokenizer {
    private final List<TokenParser> parsers;

    public Tokenizer(List<TokenParser> parsers) {
        this.parsers = parsers;
    }

    protected ParsedToken parseToken(TokenInputStream inputStream) throws TokenizerException {

        List<ParsingError> errors = new ArrayList<>();
        List<ParsedToken> tokens = new ArrayList<>();

        for (TokenParser parser : parsers) {
            try {
                ParsedToken token = parser.parse(inputStream);

                // null tokens aren't errors,
                // but they aren't worth anything either.
                if (token != null) {
                    tokens.add(token);
                }
            } catch (ParsingError e) {
                errors.add(e);
            }
        }

        // my method for disambiguating will just
        // be accepting the longest viable input.
        // it would also be possible to form a ranking,
        // or a context dependent
        // system for tokens, but this is okay for now.

        tokens.sort(Comparator.comparingInt(i -> i.getSpan().getLength()));

        if (tokens.size() == 0) {
            throw new TokenizerException(errors);
        }
        return tokens.get(tokens.size() - 1);
    }

    public List<ParsedToken> parse(MutableTokenInputStream inputStream) throws TokenizerException {
        List<ParsedToken> parsedTokens = new ArrayList<>();

        // while there isn't anything in there
        // and the last thing parsed wasn't an
        // eof, continue parsing.

        // note that this won't form an infinite loop,
        // because something must parse otherwise,
        // this method will throw.

        /* now this is a range based comment */
        while (parsedTokens.size() == 0 || parsedTokens.get(parsedTokens.size() - 1).getKind() != TokenKind.EOF) {
            try {
                ParsedToken token = parseToken(inputStream);
                inputStream.consume(token.getSpan().getLength());
                parsedTokens.add(token);
            } catch (TokenizerException e) {
                throw new PartialTokenizerException(parsedTokens, e.getParsingErrors());
            }
        }

        return parsedTokens;
    }
}