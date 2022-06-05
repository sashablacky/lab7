package banana0081.lab6.exceptions;

public class NoPathException extends FileException{
    public NoPathException(){
        super("path is empty");
    }
}
