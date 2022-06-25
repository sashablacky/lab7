package banana0081.lab6.server.commands;
import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;
public class Add implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Add(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
        int previousSize = collectionManager.getPreviousSize();
        HTTPResponse httpResponse = new HTTPResponse();
        collectionManager.add(httpRequest.getHumanBeing());
        if (previousSize == collectionManager.getSize()){
            httpResponse.pack(201, "Human being was added\n");
        } else {
            httpResponse.pack(500, "Human being could not be added\n");
        }
        return httpResponse;
    }

}
