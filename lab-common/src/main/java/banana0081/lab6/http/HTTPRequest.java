package banana0081.lab6.http;

import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.exceptions.FileDoesNotExistException;
import banana0081.lab6.exceptions.FileWrongPermissionsException;
import banana0081.lab6.exceptions.NoPathException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedList;

import static banana0081.lab6.http.HttpMethod.POST;
import static banana0081.lab6.io.OutputManager.printErr;

public class HTTPRequest implements Serializable {
    HttpMethod method;
    private Header contentType = new Header("Content-Type");
    private Header contentLength = new Header("Content-Length");
    private Header host = new Header("Host", "localhost:8080");
    private String command = new String();
    private String body = new String();
    public HTTPRequest() {}
    public void setMethod(HttpMethod method) {
        method = method;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setContentLength(Header contentLength) {
        this.contentLength = contentLength;
    }

    public Header getContentLength() {
        return contentLength;
    }

    public void setContentType(Header contentType) {
        this.contentType = contentType;
    }

    public Header getContentType() {
        return contentType;
    }

    public void setHost(Header host) {
        this.host = host;
    }

    public Header getHost() {
        return host;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() { return command;}
    public void setBody(String body) {
        this.body = body;
        contentLength.setValue(String.valueOf(body.length()));
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        String request = "";
        request += method + " /" + command + " HTTP/1.1" +'\n';
        request += host.toString() + '\n';
        if(body.length()>0) {
            request += contentType.toString() + '\n';
            request += contentLength.toString() + '\n';
            request += '\n' + body + '\n';
        }
        return request;
    }
    public HumanBeing getHumanBeing() {
        HumanBeing humanBeing = null;
        try{
            XmlMapper xmlMapper = new XmlMapper();
            humanBeing = xmlMapper.readValue(body, new TypeReference<HumanBeing>() {});
        } catch(IOException e){
            printErr(e.getMessage());
        }
        return humanBeing;
    }

    public void pack(String nameCommand, HttpMethod method) {
        this.method = method;
        this.command = nameCommand;
    }
    public void pack(String nameCommand, HumanBeing humanBeing, HttpMethod method) {
        this.method = method;
        this.command = nameCommand;
        String data = null;
        XmlMapper mapper = new XmlMapper();
        try {
            data = mapper.writeValueAsString(humanBeing);
        }
        catch (JsonProcessingException ignored){}
        assert data != null;
        this.setBody(data);
        this.setContentType(new Header("Content-Type", "text/xml"));
    }
    public void pack(String nameCommand, String[] arg, HttpMethod method) {
        this.method = method;
        this.command = nameCommand;
        String data = null;
        XmlMapper mapper = new XmlMapper();
        try {
            data = mapper.writeValueAsString(arg);
        }
        catch (JsonProcessingException ignored){}
        assert data != null;
        this.setBody(data);
        this.setContentType(new Header("Content-Type", "text/xml"));
    }
}
