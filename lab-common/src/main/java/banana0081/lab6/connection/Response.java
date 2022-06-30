package banana0081.lab6.connection;

import banana0081.lab6.Pack;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.http.HttpParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Arrays;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class Response {
    HTTPResponse httpResponse = new HTTPResponse();
    public void response(Socket socket) throws IOException, ClassNotFoundException {
        HttpParser httpParser = new HttpParser();
        byte[] bytes = new byte[32768];

        socket.getInputStream().read(bytes);
        httpParser.parseRequest(new String(crop(bytes)));
        httpResponse = httpParser.wrapResponse();
        print(httpResponse.getHttpCode()+" "+httpResponse.getReasonPhrase());
        print(httpResponse.getBody());
    }

    public void responseShow(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[1048576];
        int offset = 0;
        InputStream inputStream = socket.getInputStream();
        int lastread = inputStream.read(bytes, 0, 65536);
        while (lastread == 65536 ) {
            offset+=lastread;
            lastread = inputStream.read(bytes, offset, 65536);
        }
        httpResponse = deserialize(bytes);
        print(httpResponse.getHttpCode()+" "+httpResponse.getReasonPhrase());
        print(httpResponse.getBody());
    }

    public HTTPResponse responseUpdate(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        socket.getInputStream().read(bytes);
        httpResponse = deserialize(bytes);
        return httpResponse;
    }

    public HTTPResponse deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        HttpParser httpParser = new HttpParser();
        httpParser.parseRequest(new String(crop(bytes)));
        httpResponse = httpParser.wrapResponse();
        return httpResponse;
    }
    public byte[] crop(byte[] data){
        int i = data.length-1;
        while(data[i]==0){
            --i;
        }
        return Arrays.copyOf(data, i);
    }
}