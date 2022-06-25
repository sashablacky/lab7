package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

public class Exit implements Command {

    HumanBeingCollectionManager collectionManager;

    Exit(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
//        collectionManager.save;
        HTTPResponse httpResponse = new HTTPResponse();
        httpResponse.pack(200, "Вы отключились от сервера\n");
        return httpResponse;
    }

}
