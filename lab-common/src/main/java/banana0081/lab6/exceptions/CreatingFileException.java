package banana0081.lab6.exceptions;
/**
 * thrown when unable to create file
 */
public class CreatingFileException extends FileException{
    public CreatingFileException(){
        super("cannot create file");
    }
}
