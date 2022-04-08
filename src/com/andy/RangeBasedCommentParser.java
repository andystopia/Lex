package com.andy;

/**
 * This is the class which parses range based commends.
 *
 * In java the "multiline" comments are actually just
 * range based. They can be contained within a line, and even
 * with inside argument params. This must be kept in mind
 * while writing this parsing method.
 */
public class RangeBasedCommentParser implements TokenParser {

    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {
        if (!TokenInputStreamHelpers.nthCharactersEquals(inputStream, 0, new char[]{'/', '*'})){
            return null;
        }

        // already ate two characters, let's look for some more
        int consumed = 2;

        while (inputStream.getNthCharacter(consumed) != null) {

            if (consumed > 2 && TokenInputStreamHelpers.nthCharactersEquals(inputStream, consumed, new char[]{'*', '/'})) {
                return new ParsedToken(TokenKind.RangeBasedComment, inputStream.makeSpan(consumed + 2));
            }
            consumed++;
        }

        throw new UnexpectedEndOfInput(this);
    }
}
