package unit;

import org.example.functions.Ln;
import org.example.functions.Log2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class Log2Test {
    private Ln ln;
    private Log2 log2;

    @BeforeEach
    void setUp() {
        ln = new Ln();
        log2 = new Log2(ln);
    }

    @Test
    @DisplayName("log2(1) should be 0")
    void testLog2One() {
        assertEquals(0, log2.calculate(1), 1e-9);
    }

    @Test
    @DisplayName("log2(2) should be 1")
    void testLog2Two() {
        assertEquals(1, log2.calculate(2), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0, 0.0",
            "2.0, 1.0",
            "4.0, 2.0",
            "8.0, 3.0",
            "16.0, 4.0"
    })
    @DisplayName("log2(x) should return correct values for powers of 2")
    void testLog2PowersOfTwo(double input, double expected) {
        assertEquals(expected, log2.calculate(input), 1e-9);
    }

    @Test
    @DisplayName("log2(x) should throw exception for x <= 0")
    void testLog2InvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> log2.calculate(0));
        assertThrows(IllegalArgumentException.class, () -> log2.calculate(-1));
    }
}
