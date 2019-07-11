package com.github.chenhao96.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public final class CodeClass {

    public static final char[] NUMBER = "0123456789".toCharArray();
    public static final char[] LESS_LETTER = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] LARGE_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static final char[] CHARACTER = new char[]{'`', '-', '=', '[', ']', '\\', ';', '\'', ',', '.', '/', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '{', '}', '|', ':', '"', '<', '>', '?'};

    private char[] characterSet;
    private ReentrantLock lock = new ReentrantLock();

    public CodeClass() {
        this(NUMBER);
    }

    public CodeClass(char[]... characterSets) {

        if (characterSets == null || characterSets.length == 0) {
            throw new IllegalArgumentException("argument can not null or nothing.");
        }

        int index = 0;
        int length = 0;
        for (char[] characterSet : characterSets) {
            if (characterSet == null || characterSet.length == 0) {
                continue;
            }
            length += characterSet.length;
        }

        if (length == 0) {
            throw new IllegalArgumentException("argument can not nothing.");
        }

        char[] tmp = new char[length];
        for (char[] characterSet : characterSets) {
            if (characterSet == null || characterSet.length == 0) {
                continue;
            }
            for (char c : characterSet) {
                tmp[index++] = c;
            }
        }

        this.characterSet = tmp;
    }

    public String createCode(int length) {
        Set<String> result = createCode(length, 1);
        return result.iterator().next();
    }

    public Set<String> createCode(int length, int size) {
        lock.lock();
        characterSet = exchangeCode(characterSet, new Random());
        char[] tmp = characterSet;
        lock.unlock();
        return createCode(length, size, tmp);
    }

    private static Set<String> createCode(int length, int size, char[] tmp) {

        Random random = new Random();
        Set<String> strings = new HashSet<>(size);
        while (strings.size() < size) {

            tmp = exchangeCode(tmp, random);
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(tmp[random.nextInt(tmp.length)]);
            }

            strings.add(sb.toString());
        }

        return strings;
    }

    private static char[] exchangeCode(char[] tmp, Random random) {

        int index;
        char swap;
        for (int i = 0; i < tmp.length; i++) {

            index = random.nextInt(tmp.length - i);
            swap = tmp[index];
            tmp[index] = tmp[tmp.length - i - 1];
            tmp[tmp.length - i - 1] = swap;
        }

        return tmp;
    }
}
