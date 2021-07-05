package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AddCreateTest2 {

    @InjectMocks
    private Add add;
    @Mock
    private ValidNumber validNumber;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addTest(){
        add.add(3,2);
        Mockito.verify(validNumber).check(3);
        Mockito.verify(validNumber).check(5);
    }
}