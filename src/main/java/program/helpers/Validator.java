package program.helpers;

public class Validator {
    protected Validator() {
    }

    public static boolean isValidStr(Object obj) {

        if(obj==null){
            return false;
        }
        if(!obj.getClass().getSimpleName().equals("String")){
            return false;
        }
        if(obj.toString().isEmpty()){
            return false;
        }
        return true;
    }
}
