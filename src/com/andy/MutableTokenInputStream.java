package com.andy;

public interface MutableTokenInputStream extends TokenInputStream{
    public void consume(int length);
}
