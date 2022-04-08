package com.andy;

public interface TokenParser {
    /**
     * The method to implement for parsing a token.
     *
     * If the token cannot be correctly interpreted at all,
     * then this method should quietly return null.
     * If it *is* right, then it should return successfully,
     * unless parsing started successfully but couldn't
     * end for some reason, such as EOF, or if the parser runs out of input,
     * in which case the method should throw.
     *
     * @param inputStream
     * @return a parsed token for the next step.
     * @throws ParsingError an error that could happen while parsing, such as EOL/EOF
     */
    ParsedToken parse(TokenInputStream inputStream) throws ParsingError;
}
