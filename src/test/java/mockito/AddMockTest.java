package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AddMockTest {

    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;
    @Mock
    private Print print;
    @Captor
    private ArgumentCaptor<Integer> captor;
    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

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

    @Test
    public void addRealTest(){
        when(validNumber.check(3)).thenCallRealMethod();
        assertEquals(true, validNumber.check(3));

        when(validNumber.check("3")).thenCallRealMethod();
        assertEquals(false, validNumber.check("3"));
    }

    @Test
    public void addDoubleToIntThenAnswerTest(){
        Answer<Integer> answer = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 9;
            }
        };

        when(validNumber.doubleToInt(9.7)).thenAnswer(answer);
        assertEquals(18, add.addInt(9.7));
    }

    @Test
    public void patternTest(){
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);

        int result = add.add(4,5);
        assertEquals(9, result);
    }

    @Test
    public void patternTest2(){
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);

        int result = add.add(4,5);
        assertEquals(9, result);
    }

    @Test
    public void argumentMatcherTest(){
        when(validNumber.check(anyInt())).thenReturn(true);

        int result = add.add(10,5);
        assertEquals(15, result);
    }

    @Test
    public void addPrintTest(){
        when(validNumber.check(4)).thenReturn(true);
        add.addPrint(4,4);
        verify(validNumber, times(2)).check(4);
    }

    @Test
    public void addPrintTest2(){
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        add.addPrint(4,5);
        verify(validNumber).check(4);
        verify(validNumber).check(5);
    }

    @Test
    public void addPrintTest3(){
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        add.addPrint(4,5);
        verify(validNumber).check(4);
        verify(validNumber, never()).check(99);
        verify(validNumber, atLeast(1)).check(4);
        verify(validNumber, atMost(1)).check(99);

        verify(print).showMessage(9);
        verify(print, never()).showError();
    }

    @Test
    public void captorTest(){
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        add.addPrint(4,5);
        verify(print).showMessage(captor.capture());
        assertEquals(captor.getValue().intValue(), 9);
    }

    @Test
    public void spyTest(){
        spyList.add("1");
        spyList.add("2");

        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2, spyList.size());
    }

    @Test
    public void spyTest2(){
        mockList.add("1");
        mockList.add("2");

        verify(mockList).add("1");
        verify(mockList).add("2");
        when(mockList.size()).thenReturn(2);
        assertEquals(2, mockList.size());
    }
}