package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

import java.sql.Connection;
import java.util.LinkedList;

public class Shuffle implements Command {

    HumanBeingCollectionManager collectionManager;


    Shuffle(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest, Connection conn) {
        LinkedList<HumanBeing> oldHuman = collectionManager.getCollection();
        collectionManager.shuffle();
        HTTPResponse httpResponse = new HTTPResponse();
        if (oldHuman.equals(collectionManager.getCollection())){
            httpResponse.pack(200, "OK");
        }else {
            httpResponse.pack(500,"Collection was not shuffled");
        }
        return httpResponse;
    }
}
