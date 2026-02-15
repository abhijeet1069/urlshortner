package com.backend.urlshortner.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Base62Test {

    @Test
    void testEncode() {
        assertEquals("w73",Base62.encode(123445));
        assertEquals("1EIG",Base62.encode(394858));
        assertEquals("a0nX",Base62.encode(2384765));
        assertEquals("1lpoxT",Base62.encode(1232488445));
    }
}