package com.backend.urlshortner.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base62Test {

    @ParameterizedTest
    @CsvSource({
            "w73, 123445",
            "1EIG, 394858",
            "a0nX, 2384765",
            "1lpoxT, 1232488445"
    })
    void testEncode(String expectedBase62, Long num) {
        assertEquals(expectedBase62,Base62.encode(num));
    }

    @ParameterizedTest
    @CsvSource({
            "123445, w73",
            "394858, 1EIG",
            "2384765, a0nX",
            "1232488445, 1lpoxT"
    })
    void testDecode(Long expectedNum, String base62) {
        assertEquals(expectedNum,Base62.decode(base62));
    }
}