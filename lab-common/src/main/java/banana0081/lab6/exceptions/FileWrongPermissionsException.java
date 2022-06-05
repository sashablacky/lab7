package banana0081.lab6.exceptions;
/**
 * thrown when not enough permission to access the file
 */
public class FileWrongPermissionsException extends FileException{
    public FileWrongPermissionsException(String s){
        super(s);
    }
}