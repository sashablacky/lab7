package banana0081.lab6.server.interfaces;

import banana0081.lab6.Pack;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;

public interface Command {
    HTTPResponse execute(HTTPRequest httpRequest);
}
