package com.andy;

import java.util.List;

public class TokenizerException extends Exception {

    private final List<ParsingError> parsingErrors;


    public TokenizerException(List<ParsingError> parsingErrors) {
        this.parsingErrors = parsingErrors;
    }

    public List<ParsingError> getParsingErrors() {
        return parsingErrors;
    }
    @Override
    public String toString() {
        return "TokenizerException{" +
                "parsingErrors=" + parsingErrors +
                '}';
    }
}
