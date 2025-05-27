package unit;

import org.example.functions.Cos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CosTest {
    private Cos cos;

    @BeforeEach
    void setUp() {
        cos = new Cos();
    }

    @Test
    @DisplayName("cos(0) should be 1")
    void testCosZero() {
        assertEquals(1, cos.calculate(0), 1e-9);
    }

    @ParameterizedTest
    @CsvSource({
            "1.5707963267948966, 0.0",  // π/2
            "3.141592653589793, -1.0",  // π
            "4.71238898038469, 0.0",    // 3π/2
            "6.283185307179586, 1.0"    // 2π
    })
    @DisplayName("cos(x) should return correct values for special angles")
    void testCosSpecialAngles(double angle, double expected) {
        assertEquals(expected, cos.calculate(angle), 1e-9);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -5.0, -1.0, 1.0, 5.0, 10.0})
    @DisplayName("cos(x) should be in range [-1, 1]")
    void testCosRange(double input) {
        double result = cos.calculate(input);
        assertTrue(result >= -1.0 && result <= 1.0);
    }

    @Test
    @DisplayName("cos(NaN) should return NaN")
    void testCosNaN() {
        assertTrue(Double.isNaN(cos.calculate(Double.NaN)));
    }

    @Test
    @DisplayName("cos(±Infinity) should return NaN")
    void testCosInfinity() {
        assertTrue(Double.isNaN(cos.calculate(Double.POSITIVE_INFINITY)));
        assertTrue(Double.isNaN(cos.calculate(Double.NEGATIVE_INFINITY)));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, -1.0, 0.0, 1.0, Math.PI})
    @DisplayName("cos(x) should be close to Math.cos(x)")
    void testCosApproximation(double x) {
        assertEquals(Math.cos(x), cos.calculate(x), 1e-6);  // допускаем чуть большую погрешность
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1.0, -2.5, 3.1415})
    @DisplayName("cos(x) should be periodic with period 2π")
    void testCosPeriodicity(double x) {
        double twoPi = 2 * Math.PI;
        assertEquals(cos.calculate(x), cos.calculate(x + twoPi), 1e-9);
        assertEquals(cos.calculate(x), cos.calculate(x - twoPi), 1e-9);
    }
}
