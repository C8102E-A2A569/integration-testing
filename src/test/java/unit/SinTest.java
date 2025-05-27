package unit;

import org.example.functions.Sin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SinTest {
    private Sin sin;

    @BeforeEach
    void setUp() {
        sin = new Sin();
    }

    @Test
    @DisplayName("sin(0) should be 0")
    void testSinZero() {
        assertEquals(0, sin.calculate(0), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "0.0, 0.0",
            "1.5707963267948966, 1.0",  // π/2
            "3.141592653589793, 0.0",   // π
            "4.71238898038469, -1.0",   // 3π/2
            "6.283185307179586, 0.0"    // 2π
    })
    @DisplayName("sin(x) should return correct values for special angles")
    void testSinSpecialAngles(double angle, double expected) {
        assertEquals(expected, sin.calculate(angle), 1e-9);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -5.0, -1.0, 1.0, 5.0, 10.0})
    @DisplayName("sin(x) should be in range [-1, 1]")
    void testSinRange(double input) {
        double result = sin.calculate(input);
        assertTrue(result >= -1.0 && result <= 1.0);
    }
    @Test
    @DisplayName("sin(NaN) should return NaN")
    void testSinNaN() {
        assertTrue(Double.isNaN(sin.calculate(Double.NaN)));
    }

    @Test
    @DisplayName("sin(+∞) and sin(-∞) should return NaN")
    void testSinInfinity() {
        assertTrue(Double.isNaN(sin.calculate(Double.POSITIVE_INFINITY)));
        assertTrue(Double.isNaN(sin.calculate(Double.NEGATIVE_INFINITY)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -1.0, 0.0, 1.0, Math.PI})
    @DisplayName("sin(x) should be approximately equal to Math.sin(x)")
    void testSinApproximation(double x) {
        assertEquals(Math.sin(x), sin.calculate(x), 1e-6);  // чуть выше погрешность
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2 * Math.PI, -Math.PI, 0, Math.PI, 2 * Math.PI})
    @DisplayName("sin(x) should be periodic with period 2π")
    void testSinPeriodicity(double x) {
        double twoPi = 2 * Math.PI;
        assertEquals(sin.calculate(x), sin.calculate(x + twoPi), 1e-9);
        assertEquals(sin.calculate(x), sin.calculate(x - twoPi), 1e-9);
    }
}
