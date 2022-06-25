package banana0081.lab6.connection;

import banana0081.lab6.Pack;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.http.HttpParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class Response {
    HTTPResponse httpResponse = new HTTPResponse();
    public void response(Socket socket) throws IOException, ClassNotFoundException {
        HttpParser httpParser = new HttpParser();
        byte[] bytes = new byte[32768];
        socket.getInputStream().read(bytes);
        httpParser.parseRequest(new String(bytes));
        httpResponse = httpParser.wrapResponse();
        print(httpResponse.getBody());
    }

    public void responseShow(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        InputStream inputStream = socket.getInputStream();
        socket.shutdownOutput();
        while (inputStream.read(bytes) > -1) {
            httpResponse = deserialize(bytes);
            print(httpResponse.getBody());
        }
    }

    public HTTPResponse responseUpdate(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        socket.getInputStream().read(bytes);
        httpResponse = deserialize(bytes);
        if (!httpResponse.getBody().equals(""))
            printErr(httpResponse.getBody());
        return httpResponse;
    }

    public HTTPResponse deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        HttpParser httpParser = new HttpParser();
        httpParser.parseRequest(new String(bytes));
        httpResponse = httpParser.wrapResponse();
        return httpResponse;
    }
}