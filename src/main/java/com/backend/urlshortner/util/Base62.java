package com.backend.urlshortner.util;

public final class Base62 {

    private static final char[] ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private Base62() {
        // Utility class, don't instantiate
    }

    public static String encode(long num) {
        if (num < 0)
            throw new IllegalArgumentException("Number must be non-negative");

        if (num == 0)
            return "0";

        char[] buffer = new char[11]; // Long.MAX_VALUE needs at most 11 Base62 chars
        int index = buffer.length;

        while (num > 0) {
            buffer[--index] = ALPHABET[(int) (num % 62)];
            num /= 62;
        }

        return new String(buffer, index, buffer.length - index);
    }

    public static long decode(String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException("Base62 value cannot be null or empty");

        long result = 0;

        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            int digit = decodeChar(c);

            // Detect long overflow before multiplication/addition
            if (result > (Long.MAX_VALUE - digit) / 62) {
                throw new IllegalArgumentException("Base62 value is too large");
            }
            result = result * 62 + digit;
        }
        return result;
    }

    private static int decodeChar(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }

        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 10;
        }

        if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 36;
        }

        throw new IllegalArgumentException(
                "Invalid Base62 character: " + c
        );
    }
}