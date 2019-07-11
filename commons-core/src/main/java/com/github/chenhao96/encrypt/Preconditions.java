package com.github.chenhao96.encrypt;

public class Preconditions {

    public static void checkArgument(boolean result, String msg) {

        if (!result) {
            throw new IllegalArgumentException(msg);
        }
    }
}
