package unit;

import org.example.functions.Ln;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class LnTest {
    private Ln ln;

    @BeforeEach
    void setUp() {
        ln = new Ln();
    }

    @Test
    @DisplayName("ln(1) should be 0")
    void testLnOne() {
        assertEquals(0, ln.calculate(1), 1e-9);
    }

    @Test
    @DisplayName("ln(e) should be 1")
    void testLnE() {
        assertEquals(1, ln.calculate(Math.E), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "1.0, 0.0",
            "2.0, 0.6931471805599453",
            "5.0, 1.6094379124341003",
            "10.0, 2.302585092994046"
    })
    @DisplayName("ln(x) should return correct values for known inputs")
    void testLnKnownValues(double input, double expected) {
        assertEquals(expected, ln.calculate(input), 1e-9);
    }

    @Test
    @DisplayName("ln(x) should throw exception for x <= 0")
    void testLnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> ln.calculate(0));
        assertThrows(IllegalArgumentException.class, () -> ln.calculate(-1));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.5, 1.5, 2.0, 100.0})
    @DisplayName("ln(x) should be within reasonable bounds")
    void testLnRange(double input) {
        double result = ln.calculate(input);
        if (input < 1) {
            assertTrue(result < 0);
        } else {
            assertTrue(result >= 0);
        }
    }
}

