package integration;
import org.example.SystemFunction;
import org.example.functions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SystemFunctionLogTest {
    private SystemFunction system;
    @Mock
    private LogFunctions logFunctions;
    @Mock
    private Ln ln;
    @Mock
    private Log2 log2;
    @Mock
    private Log5 log5;
    @Mock
    private Log10 log10;

    @BeforeEach
    void setUp() {
        when(logFunctions.getLn()).thenReturn(ln);
        when(logFunctions.getLog2()).thenReturn(log2);
        when(logFunctions.getLog5()).thenReturn(log5);
        when(logFunctions.getLog10()).thenReturn(log10);

        // Тригонометрические функции не используются для x > 0, можно замокать
        TrigFunctions trigFunctions = mock(TrigFunctions.class);
        system = new SystemFunction(trigFunctions, logFunctions);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.5, 2.0, 5.0})
    @DisplayName("Test System function for x > 0")
    void testSystemForPositiveValues(double x) {
        when(ln.calculate(x)).thenReturn(Math.log(x));
        when(log2.calculate(x)).thenReturn(Math.log(x)/Math.log(2));
        when(log5.calculate(x)).thenReturn(Math.log(x)/Math.log(5));
        when(log10.calculate(x)).thenReturn(Math.log(x)/Math.log(10));

        double result = system.calculate(x);
        assertTrue(Double.isFinite(result));
    }

    @Test
    @DisplayName("Test special case x = 1.0")
    void testSpecialCaseAtOne() {
        when(ln.calculate(1.0)).thenReturn(0.0);
        when(log2.calculate(1.0)).thenReturn(0.0);
        when(log5.calculate(1.0)).thenReturn(0.0);
        when(log10.calculate(1.0)).thenReturn(0.0);

        assertEquals(0.0, system.calculate(1.0), 1e-9);
    }
}
