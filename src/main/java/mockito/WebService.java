package mockito;

public class WebService {

    public void login(String user, String pass, Callback callback){
        if (checkLogin(user,pass)){
            callback.onSuccess("Usuario correcto");
        }else {
            callback.onFail("Error al conectar");
        }
    }

    public boolean checkLogin(String user,  String pass){
        if (user.equals("alvaro") && pass.equals("jamon")){
            return true;
        }else{
            return false;
        }
    }
}
