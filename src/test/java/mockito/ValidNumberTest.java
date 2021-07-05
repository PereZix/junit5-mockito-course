package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        validNumber = new ValidNumber();
    }

    @AfterEach
    public void tearDown(){
        validNumber = null;
    }

    @Test
    public void checkTest(){
        assertEquals(true, validNumber.check(5));
    }

    @Test
    public void checkNegativeTest(){
        assertEquals(false, validNumber.check(-1));
    }

    @Test
    public void checkStringTest(){
        assertEquals(true, validNumber.check("5"));
    }

    @Test
    public void checkZeroTest(){
        assertEquals(true, validNumber.checkZero(-57));
    }

    @Test
    public void checkZeroStringTest(){
        assertEquals(false, validNumber.checkZero("5"));
    }

    @Test
    public void checkZero0Test(){
        assertThrows(ArithmeticException.class, ()->validNumber.checkZero(0));
    }
}