package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.junit.jupiter.api.Assertions.*;

class WebServiceTest {

    private WebService webService;
    @Mock
    private Callback callback;

    @BeforeEach
    void setUp() {
        webService = new WebService();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkLoginTest(){
        assertTrue(webService.checkLogin("alvaro", "jamon"));
    }

    @Test
    public void checkLoginError(){
        assertTrue(webService.checkLogin("ana","azul"));
    }

    @Test
    public void loginTest(){
        webService.login("alvaro","jamon", callback);
        Mockito.verify(callback).onSuccess("Usuario correcto");
    }

    @Test
    public void loginErrorTest(){
        webService.login("alvaroooo","amarillo", callback);
        Mockito.verify(callback).onFail("Error al conectar");
    }
}