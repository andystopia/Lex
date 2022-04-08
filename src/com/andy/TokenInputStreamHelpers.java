package com.andy;

import java.util.function.Predicate;

public class TokenInputStreamHelpers {
    public static boolean nthCharacterEquals(char character, int nthIndex, TokenInputStream stream) {
        Character ch = stream.getNthCharacter(nthIndex);
        return ch != null && ch == character;
    }

    public static boolean nthCharacterAnyOf(TokenInputStream stream, int n, char[] characters) {
        for (char character : characters) {
            if (nthCharacterEquals(character, n, stream)) {
                return true;
            }
        }
        return false;
    }
    /**
     * note won't work if you the predicate checks nullness!
     * @param stream
     * @param n
     * @param test
     * @return
     */
    public static boolean nthCharacterSatisfies(TokenInputStream stream, int n, Predicate<Character> test) {
        Character ch = stream.getNthCharacter(n);

        if (ch == null) {
            return false;
        }

        return test.test(ch);
    }

    public static boolean nthCharactersEquals(TokenInputStream stream, int n, char[] characters) {
        int i = n;
        while (stream.getNthCharacter(i) != null) {
            if (i - n == characters.length) {
                return true;
            }

            char c = stream.getNthCharacter(i);
            if (c != characters[i - n]) {
                return false;
            }
            i++;
        }
        return false;
    }
}
