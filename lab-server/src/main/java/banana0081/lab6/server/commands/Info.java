package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

public class Info implements Command {
    private final HumanBeingCollectionManager collectionManager;
    private String[] Information = new String[1];

    public Info(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String[] getInfo(){
        return Information;
    }
    public void setInfo(String info){
        this.Information[0] = info;
    }
    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();
        this.setInfo(collectionManager.getInfo());
        httpResponse.pack(Information, 200, "OK");
        return httpResponse;
    }

}