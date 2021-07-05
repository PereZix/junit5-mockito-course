package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AddMockTest {

    @Mock
    private ValidNumber validNumber;
    @InjectMocks
    private Add add;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addTest(){
        when(validNumber.check(3)).thenReturn(false);
        boolean checkNumber = validNumber.check(3);
        assertEquals(false, checkNumber);

        when(validNumber.check("a")).thenReturn(false);
        checkNumber = validNumber.check("a");
        assertEquals(false, checkNumber);
    }

    @Test
    public void addMockExceptionTest(){
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero"));
        Exception exception = null;
        try{
            validNumber.checkZero(0);
        }catch (ArithmeticException e){
            exception = e;
        }

        assertNotNull(exception);
    }
}