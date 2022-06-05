package banana0081.lab6.exceptions;
/**
 * base class for all exceptions linked with invalid input
 */
public class InvalidDataException extends Exception{
    public InvalidDataException(String message) {
        super(message);
    }
}