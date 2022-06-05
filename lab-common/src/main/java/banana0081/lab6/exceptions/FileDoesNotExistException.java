package banana0081.lab6.exceptions;
/**
 * thrown when file doesnt exist
 */
public class FileDoesNotExistException extends FileException{
    public FileDoesNotExistException(){
        super("cannot find file");
    }
}
