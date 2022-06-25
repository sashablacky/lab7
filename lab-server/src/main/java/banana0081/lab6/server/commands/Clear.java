package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

public class Clear implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Clear(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
        collectionManager.clear();
        HTTPResponse httpResponse = new HTTPResponse();
        if (collectionManager.getCollection().isEmpty()){
            httpResponse.pack(200, "OK");
        } else{
            httpResponse.pack(500,"Internal Error");
        }
        return httpResponse;
    }
}
