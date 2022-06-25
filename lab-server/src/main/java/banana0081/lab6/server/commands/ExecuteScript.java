package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.CommandWithArguments;

public class ExecuteScript implements CommandWithArguments {

    String[] arguments;

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();
        httpResponse.pack(200, "OK");
        return httpResponse;
    }

    @Override
    public void getArgs(String[] arguments) {
        this.arguments = arguments;
    }
}
