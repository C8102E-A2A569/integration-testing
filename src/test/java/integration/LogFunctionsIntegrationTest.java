package integration;

import org.example.functions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogFunctionsIntegrationTest {
    @Mock private Ln ln;
    private Log2 log2;
    private Log5 log5;
    private Log10 log10;

    @BeforeEach
    void setUp() {
        log2 = new Log2(ln);
        log5 = new Log5(ln);
        log10 = new Log10(ln);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 2.0, 5.0, 10.0, 20.0})
    @DisplayName("Integration: Log functions work with ln as base")
    void testLogIntegrationWithLn(double x) {
        when(ln.calculate(x)).thenAnswer(invocation -> Math.log(x));

        assertEquals(Math.log(x) / Math.log(2), log2.calculate(x), 1e-9);
        assertEquals(Math.log(x) / Math.log(5), log5.calculate(x), 1e-9);
        assertEquals(Math.log(x) / Math.log(10), log10.calculate(x), 1e-9);

        verify(ln, times(3)).calculate(x);
    }

    @Test
    @DisplayName("Integration: Log identity verification")
    void testLogIdentities() {
        double testValue = 25.0;

        when(ln.calculate(testValue)).thenReturn(Math.log(testValue));
        when(ln.calculate(2)).thenReturn(Math.log(2));


        double log2x = log2.calculate(testValue);
        double log52 = log5.calculate(2);
        double log5x = log5.calculate(testValue);

        assertEquals(log5x, log2x * log52, 1e-9);
    }

    @Test
    @DisplayName("Integration: Log power law verification")
    void testLogPowerLaw() {
        double base = 3.0;
        double exponent = 4.0;
        double result = Math.pow(base, exponent);

        when(ln.calculate(base)).thenReturn(Math.log(base));
        when(ln.calculate(result)).thenReturn(Math.log(result));

        assertEquals(exponent * ln.calculate(base), ln.calculate(result), 1e-9);
    }
    @ParameterizedTest
    @ValueSource(doubles = {
            Double.MIN_VALUE, Double.MAX_VALUE, 0.0, -1.0,
            Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY
    })
    @DisplayName("Integration: Log functions edge cases with ln as base")
    void testLogFunctionsWithEdgeCases(double x) {
        // ln.calculate(x) должен быть стабилизирован: выбрасывать исключения или возвращать NaN.
        if (x <= 0.0 || Double.isNaN(x)) {
            when(ln.calculate(x)).thenThrow(new IllegalArgumentException("ln(x) undefined for x <= 0 or NaN"));
            assertThrows(IllegalArgumentException.class, () -> log2.calculate(x));
            assertThrows(IllegalArgumentException.class, () -> log5.calculate(x));
            assertThrows(IllegalArgumentException.class, () -> log10.calculate(x));
        } else {
            when(ln.calculate(x)).thenAnswer(invocation -> Math.log(x));

            assertEquals(Math.log(x) / Math.log(2), log2.calculate(x), 1e-9);
            assertEquals(Math.log(x) / Math.log(5), log5.calculate(x), 1e-9);
            assertEquals(Math.log(x) / Math.log(10), log10.calculate(x), 1e-9);

            verify(ln, times(3)).calculate(x);
        }
    }
}
