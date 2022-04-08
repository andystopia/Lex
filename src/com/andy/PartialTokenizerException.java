package com.andy;

import java.util.List;

public class PartialTokenizerException extends TokenizerException {


    @Override
    public String toString() {
        return "PartialTokenizerException{" +
                "successfullyParsed=" + successfullyParsed
                + "{" + super.toString() + "}" +
                '}';
    }

    private final List<ParsedToken> successfullyParsed;

    public PartialTokenizerException(List<ParsedToken> successfullyParsed, List<ParsingError> parsingErrors) {
        super(parsingErrors);

        this.successfullyParsed = successfullyParsed;
    }

    public List<ParsedToken> getSuccessfullyParsed() {
        return successfullyParsed;
    }
}
