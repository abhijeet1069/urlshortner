package com.backend.urlshortner.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Base62 encoding and decoding is fundamental invariant for my application
public class Base62Test {


    @ParameterizedTest
    @ValueSource(longs = {
            0L,
            1L,
            10L,
            61L,
            62L,
            1000L,
            Long.MAX_VALUE
    })
    void encode_and_decode_should_be_inverse_of_each_other(long value) {
        assertEquals(value,Base62.decode(Base62.encode(value)));
    }

    @Test
    void encode_should_reject_negative_number() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Base62.encode(-1)
        );
    }

    @Test
    void encode_should_reject_null() {
        assertThrows(IllegalArgumentException.class,
                () -> Base62.decode(null)
        );
    }

    @Test
    void encode_should_reject_empty_string() {
        assertThrows(IllegalArgumentException.class,
                () -> Base62.decode("")
        );
    }

    @Test
    void decode_should_reject_invalid_character() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Base62.decode("@")
        );
    }

    @Test
    void decode_should_reject_overflow() {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> Base62.decode("ZZZZZZZZZZZZ")
        );
        assertEquals("Base62 value is too large",exception.getMessage());
    }
}