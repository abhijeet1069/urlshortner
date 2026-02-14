package com.backend.urlshortner.util;

public class Base62 {

    private static final String ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long num) {
        if (num == 0) return "0";

        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int rem = (int)(num % 62);
            sb.append(ALPHABET.charAt(rem));
            num /= 62;
        }

        return sb.reverse().toString();
    }
}