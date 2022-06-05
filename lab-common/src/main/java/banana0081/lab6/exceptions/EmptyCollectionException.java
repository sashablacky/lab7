package banana0081.lab6.exceptions;
/**
 * thrown when collection is empty
 */
public class EmptyCollectionException extends CommandException{
    public EmptyCollectionException(){
        super("Collection is empty");
    }
}
