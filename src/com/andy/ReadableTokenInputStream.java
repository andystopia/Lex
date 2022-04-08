package com.andy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;

public class ReadableTokenInputStream extends BaseTokenInputStream{
    private final BufferedReader readable;

    private final StringBuilder backing = new StringBuilder();


    /**
     * Pass something like a stringbuilder in for backing,
     * so that you can append without reallocing.
     * @param readable
     */
    public ReadableTokenInputStream(Reader readable) {
        this.readable = new BufferedReader(readable);
    }

    @Override
    public void consume(int length) {
        startingValue += length;
    }

    @Override
    public Character getNthCharacter(int n)  {
        while (n + startingValue >= backing.length()) {
            try {
                // man is that a hack, but readline
                // consumes newlines, and I need newlines for parsing.
                // and I'm not willing to make custom buffers today.
                String line = readable.readLine();
                if (line == null) {
                    return null;
                }
                backing.append(line + '\n');
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return null;
            }
        }

        return backing.charAt(startingValue + n);
    }
}
