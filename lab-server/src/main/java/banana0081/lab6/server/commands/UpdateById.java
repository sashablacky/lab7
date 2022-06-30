package banana0081.lab6.server.commands;

import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.CommandWithArguments;

public class UpdateById implements CommandWithArguments {
    private final HumanBeingCollectionManager collectionManager;
    private String[] arg;

    public UpdateById(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
        int previousSize = collectionManager.getPreviousSize();
        HTTPResponse httpResponse = new HTTPResponse();
        if (collectionManager.updateByID(httpRequest.getHumanBeing().getId(), httpRequest.getHumanBeing())){
            httpResponse.pack(200, "Updated");
        } else {
            httpResponse.pack(500, "Internal Error");
        }
        return httpResponse;
    }

    @Override
    public void getArgs(String[] arg) {
        this.arg = arg;
    }
}
