package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.CommandWithArguments;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RemoveById implements CommandWithArguments {
    private HumanBeingCollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    RemoveById (HumanBeingCollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();
        for (String argument : arguments) {
            collectionManager.removeByID(Integer.parseInt(argument));
        }
        httpResponse.pack(200, "OK");
        return httpResponse;
    }
    @Override
    public void getArgs(String[] arguments) {
        this.arguments = arguments;
    }
}
