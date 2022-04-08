package com.andy;

public class SingleLineCommentParser implements TokenParser {
    @Override
    public ParsedToken parse(TokenInputStream inputStream) throws ParsingError {
        // this is not a comment, if this case isn't valid,
        // otherwise it is a comment.
        if (!(TokenInputStreamHelpers.nthCharacterEquals('/', 0, inputStream) &&
            TokenInputStreamHelpers.nthCharacterEquals('/', 1, inputStream))
        ) {
            return null;
        }

        int consumed = 2;
        while (!TokenInputStreamHelpers.nthCharacterEquals('\n', consumed, inputStream)) {
            consumed++;
        }

        return new ParsedToken(TokenKind.LineComment, inputStream.makeSpan(consumed));
    }
}
