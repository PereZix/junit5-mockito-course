package junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;
    private Calculator calculatorNull;
    private static Calculator calculatorStatic;

    @BeforeAll
    public static void setUpAll(){
        calculatorStatic = new Calculator();
        System.out.println("@BeforeEach -> setUpAll()");
    }

    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
        System.out.println("@BeforeEach -> setUp()");
    }

    @AfterAll
    public static void afterAll(){
        calculatorStatic = new Calculator();
        System.out.println("@AfterAll -> afterAll()");
    }

    @AfterEach
    public void tearDown(){
        calculator = null;
        System.out.println("@AfterEach -> tearDown()");
    }

    @Test
    public void calculatorNotNullTest(){
        assertNotNull(calculator, "Calculator debe ser not null");
        System.out.println("@Test -> calculatorNotNullTest()");
    }

    @Test
    public void calculatorNullTest(){
        assertNull(calculatorNull);
        System.out.println("@Test -> calculatorNullTest()");
    }

    @Test
    public void addAssertTest(){
        //  SetUp
        int resultEsperado = 30;
        int resultResponse;

        //  Action
        resultResponse = calculator.add(10, 20);

        //  Assert
        assertEquals(resultEsperado, resultResponse);
        System.out.println("@Test -> addAssertTest()");
    }

    @Test
    public void addTest(){
        assertEquals(30, calculator.add(10,20));
    }

    @Test
    public void assertTypesTest(){
        assertTrue(1 == 1);
        assertNull(calculatorNull);
        assertNotNull(calculator);

        Calculator calculator1 = new Calculator();
        Calculator calculator2 = new Calculator();
        Calculator calculator3 = calculator1;

        assertSame(calculator1, calculator3);
        assertNotSame(calculator1, calculator2);

        assertEquals("alvaro", "alvaro");

        assertEquals(1, 1.4, 0.5);
    }

    @Test
    public void add_ValidInput_ValidExpected_Test(){
        assertEquals(11, calculator.add(7,4));
    }

    @Test
    public void subtract_ValidInput_ValidExpected_Test(){
        assertEquals(11, calculator.subtract(15,4));
    }

    @Test
    public void subtract_ValidInput_ValidNegativaResultExpected_Test(){
        assertEquals(-10, calculator.subtract(10,20));
    }

    @Test
    public void divideTest(){
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    public void divideByZeroFailTest(){
        assertEquals(2, calculator.divide(10, 0));
        fail("Fallo detectado manualmente - No se puede dividir por cero");
    }

    @Test
    public void divideByZeroExpectedExceptionTest(){
        assertThrows(ArithmeticException.class, ()->calculator.divideByZero(2,0), "No se puede dividir por cero");
    }

    @Disabled("Porque no quiero correclo")
    public void divideInvalidTest(){
        assertEquals(2, calculator.divide(5,0));
    }

    @Test
    @DisplayName("Metodo dividir con metodo displayNam")
    public void displayNameTest(){
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    public void addAssertAllTest(){
        assertAll(
                ()-> assertEquals(31, calculator.add(11,20)),
                ()-> assertEquals(30, calculator.add(10,20)),
                ()-> assertEquals(2, calculator.add(1,1))
        );
    }

    @Test
    public void timeoutTest(){
        assertTimeout(Duration.ofMillis(2000), ()->{
            calculator.longTaskOperation();
        });
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("addProviderData")
    public void addParameterizedTest(int a, int b, int sum){
        assertEquals(sum, calculator.add(a,b));
    }

    private static Stream<Arguments> addProviderData(){
        return Stream.of(
                Arguments.of(6,2,8),
                Arguments.of(-6,-2,8),
                Arguments.of(1,3,5),
                Arguments.of(7,6,8),
                Arguments.of(6,-2,4)
        );
    }

    @Nested
    class AddTest{
        @Test
        public void addPositiveTest(){
            assertEquals(30, calculator.add(15,15));
        }
        @Test
        public void addNegativeTest(){
            assertEquals(-30, calculator.add(-15,-15));
        }
        @Test
        public void addZeroTest(){
            assertEquals(0, calculator.add(0,0));
        }
    }
}