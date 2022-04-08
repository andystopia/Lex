package com.andy;

public interface TokenInputStream {
    Character getNthCharacter(int n);
    Span makeSpan(int consumed);
}
