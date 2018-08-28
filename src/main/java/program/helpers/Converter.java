package program.helpers;

public class Converter {
    protected Converter() {
    }
    public static String convertToPascalCase(String input){
        if(Validator.isValidStr(input)){
            return input.substring(0,1).toUpperCase()+input.substring(1).toLowerCase();
        }
        return input;
    }
}
