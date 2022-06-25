package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

public class Show implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Show(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
        String s = "";
        HTTPResponse httpResponse = new HTTPResponse();
        if(collectionManager.getSize()>0) {
            String[] res = new String[collectionManager.getSize()];
            int i = 0;
            for (HumanBeing humanBeing : collectionManager.getCollection()) {
                s = humanBeing.toString();
                res[i] = s;
                i++;
            }
            httpResponse.pack(res, 200, "OK");
        }else{s="Collection is empty";
            httpResponse.pack(new String[]{"Collection is empty"}, 200, "OK");}

        return httpResponse;
    }
}