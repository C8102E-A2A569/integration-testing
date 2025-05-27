package integration;

import org.example.functions.Cos;
import org.example.functions.Sin;
import org.example.functions.TrigFunctions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrigFunctionsIntegrationTest {
    @Mock private Sin sin;
    @Mock
    private Cos cos;
    private TrigFunctions trigFunctions;

    @BeforeEach
    void setUp() {
        trigFunctions = new TrigFunctions(sin, cos);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, Math.PI/6, Math.PI/4, Math.PI/3, Math.PI/2, Math.PI, -Math.PI/2})
    @DisplayName("Integration: Sin and Cos implementations work together")
    void testSinCosIntegration(double x) {
        when(sin.calculate(x)).thenReturn(Math.sin(x));
        when(cos.calculate(x)).thenReturn(Math.cos(x));

        double sinValue = trigFunctions.getSin().calculate(x);
        double cosValue = trigFunctions.getCos().calculate(x);

        assertEquals(1.0, sinValue*sinValue + cosValue*cosValue, 1e-9);

        verify(sin).calculate(x);
        verify(cos).calculate(x);
    }

    @Test
    @DisplayName("Integration: Verifies sin and cos for common angles")
    void testCommonAngles() {
        // Test 0 degrees
        when(sin.calculate(0)).thenReturn(0.0);
        when(cos.calculate(0)).thenReturn(1.0);
        assertEquals(0.0, trigFunctions.getSin().calculate(0), 1e-9);
        assertEquals(1.0, trigFunctions.getCos().calculate(0), 1e-9);
        verify(sin).calculate(0);
        verify(cos).calculate(0);

        // Test π/2 (90 degrees)
        when(sin.calculate(Math.PI/2)).thenReturn(1.0);
        when(cos.calculate(Math.PI/2)).thenReturn(0.0);
        assertEquals(1.0, trigFunctions.getSin().calculate(Math.PI/2), 1e-9);
        assertEquals(0.0, trigFunctions.getCos().calculate(Math.PI/2), 1e-9);
        verify(sin).calculate(Math.PI/2);
        verify(cos).calculate(Math.PI/2);
    }
}
