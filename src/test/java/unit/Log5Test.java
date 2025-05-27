package unit;

import org.example.functions.Ln;
import org.example.functions.Log5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
class Log5Test {
    private Ln ln;
    private Log5 log5;

    @BeforeEach
    void setUp() {
        ln = new Ln();
        log5 = new Log5(ln);
    }

    @Test
    @DisplayName("log5(1) should be 0")
    void testLog5One() {
        assertEquals(0, log5.calculate(1), 1e-9);
    }

    @Test
    @DisplayName("log5(5) should be 1")
    void testLog5Five() {
        assertEquals(1, log5.calculate(5), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0, 0.0",
            "5.0, 1.0",
            "25.0, 2.0",
            "125.0, 3.0"
    })
    @DisplayName("log5(x) should return correct values for powers of 5")
    void testLog5PowersOfFive(double input, double expected) {
        assertEquals(expected, log5.calculate(input), 1e-9);
    }

    @Test
    @DisplayName("log5(x) should throw exception for x <= 0")
    void testLog5InvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> log5.calculate(0));
        assertThrows(IllegalArgumentException.class, () -> log5.calculate(-1));
    }
}
