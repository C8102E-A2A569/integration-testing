package unit;

import org.example.functions.Ln;
import org.example.functions.Log10;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
class Log10Test {
    private Ln ln;
    private Log10 log10;

    @BeforeEach
    void setUp() {
        ln = new Ln();
        log10 = new Log10(ln);
    }

    @Test
    @DisplayName("log10(1) should be 0")
    void testLog10One() {
        assertEquals(0, log10.calculate(1), 1e-9);
    }

    @Test
    @DisplayName("log10(10) should be 1")
    void testLog10Ten() {
        assertEquals(1, log10.calculate(10), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0, 0.0",
            "10.0, 1.0",
            "100.0, 2.0",
            "1000.0, 3.0"
    })
    @DisplayName("log10(x) should return correct values for powers of 10")
    void testLog10PowersOfTen(double input, double expected) {
        assertEquals(expected, log10.calculate(input), 1e-9);
    }

    @Test
    @DisplayName("log10(x) should throw exception for x <= 0")
    void testLog10InvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> log10.calculate(0));
        assertThrows(IllegalArgumentException.class, () -> log10.calculate(-1));
    }
}
