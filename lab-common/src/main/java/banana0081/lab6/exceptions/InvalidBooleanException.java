package banana0081.lab6.exceptions;

/**
 * thrown when boolean is not true or false
 */
public class InvalidBooleanException extends InvalidDataException {
    public InvalidBooleanException(){
        super("Boolean must be true or false");
    }
    public InvalidBooleanException(String msg){
        super(msg);
    }
}
