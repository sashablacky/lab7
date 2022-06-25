package banana0081.lab6.http;

import banana0081.lab6.data.HumanBeing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static banana0081.lab6.io.OutputManager.printErr;
import static java.lang.String.valueOf;

public class HTTPResponse {
    private int httpCode;
    private String reasonPhrase = "";
    private Header contentType = new Header("Content-Type");
    private Header contentLength = new Header("Content-Length", "0");
    private Header host = new Header("Host", "localhost:8080");
    private String command = "";
    private String body = "";
    public HTTPResponse() {}

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

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public void setHost(Header host) {
        this.host = host;
    }

    public Header getHost() {
        return host;
    }

    public String getCommand() { return command;}
    public void setBody(String body) {
        this.body = body;
        contentLength.setValue(valueOf(body.getBytes(StandardCharsets.UTF_8).length));
    }

    public String getBody() {
        return body;
    }


    @Override
    public String toString() {
        String request = "";
        request +=  "HTTP/1.1 " + httpCode + " " + reasonPhrase + '\n';
        request += host.toString() + '\n';
        request += contentLength.toString() + '\n';
        if(body.length()>0) {
            request += contentType.toString() + '\n';
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

    public void pack(int httpCode, String reasonPhrase) {
        this.httpCode = httpCode;
        this.reasonPhrase = reasonPhrase;
    }
    public void pack(HumanBeing humanBeing, int httpCode, String reasonPhrase) {
        this.httpCode = httpCode;
        this.reasonPhrase = reasonPhrase;
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
    public void pack(String[] arg, int httpCode, String reasonPhrase) {
        this.httpCode = httpCode;
        this.reasonPhrase = reasonPhrase;
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
