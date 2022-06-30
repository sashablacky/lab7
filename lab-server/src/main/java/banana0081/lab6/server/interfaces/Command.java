package banana0081.lab6.server.interfaces;

import banana0081.lab6.Pack;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;

import java.sql.Connection;

public interface Command {
    HTTPResponse execute(HTTPRequest httpRequest, Connection conn);
}
