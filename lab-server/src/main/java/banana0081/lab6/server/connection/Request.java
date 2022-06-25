package banana0081.lab6.server.connection;


import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HttpParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class Request {
    public HTTPRequest deserialize(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        HTTPRequest httpRequest = new HTTPRequest();
        HttpParser httpParser = new HttpParser();
        byte[] array = new byte[buffer.remaining()];
        buffer.get(array);
        httpParser.parseRequest(new String(array));
        httpRequest = httpParser.wrap();
        return httpRequest;
    }
}
