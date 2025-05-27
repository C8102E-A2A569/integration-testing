package integration;
import org.example.SystemFunction;
import org.example.functions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SystemFunctionTrigTest {
    private SystemFunction system;
    private LogFunctions logFunctions;

    @BeforeEach
    void setUp() {
        // Используем реальные тригонометрические функции
        TrigFunctions trigFunctions = new TrigFunctions(new Sin(), new Cos());
        // Для тестов тригонометрической части нам не нужны моки логарифмов
        logFunctions = new LogFunctions();
        system = new SystemFunction(trigFunctions, logFunctions);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-2.0, -1.0, -0.5, -0.1})
    @DisplayName("Test System function for x < 0")
    void testSystemForNegativeValues(double x) {
        TrigFunctions trig = new TrigFunctions(new Sin(), new Cos());
        double expected = trig.getCos().calculate(x) + trig.getSin().calculate(x);
        assertEquals(expected, system.calculate(x), 1e-9);
    }

    @Test
    @DisplayName("Test System function boundary condition at x = 0")
    void testBoundaryConditionAtZero() {
        // При x = 0 проверяем только тригонометрическую часть
        TrigFunctions trig = new TrigFunctions(new Sin(), new Cos());
        double expected = trig.getCos().calculate(0) + trig.getSin().calculate(0);
        assertEquals(expected, system.calculate(0), 1e-9);
    }

    @Test
    @DisplayName("Boundary test around zero (negative side)")
    void testBoundaryAroundZeroNegative() {
        double smallNegative = -0.1;
        double expectedNegative = Math.cos(smallNegative) + Math.sin(smallNegative);
        assertEquals(expectedNegative, system.calculate(smallNegative), 1e-9);
    }
}
