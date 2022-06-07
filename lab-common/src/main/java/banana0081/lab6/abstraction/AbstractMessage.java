package banana0081.lab6.abstraction;

public abstract class AbstractMessage {
    private final String message;

    public AbstractMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
