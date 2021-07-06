package mockito;

import java.io.ObjectOutputStream;

public class ValidNumber {

    public ValidNumber(){}

    public boolean check(Object obj){
        if (obj instanceof Integer){
            if ((Integer)obj < 10 && (Integer)obj >= 0){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    public boolean checkZero(Object obj){
        if (obj instanceof Integer){
            if ((Integer)obj == 0){
                throw new ArithmeticException("No podemos aceptar cero");
            }else{
                return true;
            }
        }else {
            return false;
        }
    }

    public int doubleToInt(Object obj){
        if (obj instanceof Double){
            return ((Double) obj).intValue();
        }else{
            return 0;
        }
    }
}
