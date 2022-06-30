package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.CommandWithArguments;

import java.sql.Connection;
import java.util.Scanner;

public class Update implements CommandWithArguments {
    private String[] arguments;
    private final HumanBeingCollectionManager collectionManager;
    private Scanner in;

    public Update(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest, Connection conn) {
        HTTPResponse httpResponse = new HTTPResponse();
        Integer arg = httpRequest.getIntegerArgument();
        String user = httpRequest.getAuthorization();
        if (arg != null && collectionManager.getElementById(arg) != null) {
            String creator = collectionManager.getElementById(arg).getCreator();
            if(creator != null && creator.equals(user)) {
                httpResponse.pack(collectionManager.getElementById(arg), 200, "OK");
            }
            else{
                httpResponse.pack(403, "Forbidden");
            }
        }
        else {
            httpResponse.pack(404, "Not Found");
        }
        return httpResponse;
    }

    @Override
    public void getArgs(String[] arguments) {
        this.arguments = arguments;
    }
}