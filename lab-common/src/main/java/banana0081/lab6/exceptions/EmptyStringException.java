package banana0081.lab6.exceptions;

/**
 * thrown when user input is empty
 */
public class EmptyStringException extends InvalidDataException {
    public EmptyStringException(){
        super("String cannot be empty");
    }
}
