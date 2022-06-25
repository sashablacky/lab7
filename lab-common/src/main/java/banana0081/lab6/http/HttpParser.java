package banana0081.lab6.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Hashtable;

public class HttpParser {

    private String requestLine;
    private Hashtable<String, String> headers;
    private StringBuffer body;

    public HttpParser() {
        headers = new Hashtable<String, String>();
        body = new StringBuffer();
    }

    /**
     * Parse a HTTP request.
     *
     * @param request
     *            String holding http request.
     * @throws IOException
     *             If an I/O error occurs reading the input stream.
     */
    public void parseRequest(String request) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(request));

        requestLine = (reader.readLine()); // Request-Line ; Section 5.1

        String header = reader.readLine();
        while (header != null && header.length() > 0) {
            appendHeaderParameter(header);
            header = reader.readLine();
        }

        String bodyLine = reader.readLine();
        while (bodyLine != null) {
            appendMessageBody(bodyLine);
            bodyLine = reader.readLine();
        }
    }
    public String getRequestLine() {
        return requestLine;
    }
    public String getMessageBody() {
        return body.toString();
    }

    private void appendHeaderParameter(String header){
        int idx = header.indexOf(":");
        headers.put(header.substring(0, idx), header.substring(idx + 1, header.length()));
    }
    private void appendMessageBody(String bodyLine) {
        body.append(bodyLine).append("\r\n");
    }

    public HTTPRequest wrap() {
        HTTPRequest httpRequest = new HTTPRequest();
        try {
            httpRequest.setMethod(HttpMethod.valueOf(requestLine.split(" ")[0]));
        } catch(IllegalArgumentException e){e.printStackTrace();}
        try {
            httpRequest.setCommand(requestLine.split(" ")[1].substring(1));
        } catch (StringIndexOutOfBoundsException e){e.printStackTrace();}
        httpRequest.setBody(body.toString());
        httpRequest.setHost(new Header("host", headers.get("Host")));
        httpRequest.setContentType(new Header("Content-Type", headers.get("Content-Type")));
        httpRequest.setContentLength(new Header("Content-Length", headers.get("Content-Length")));
        return httpRequest;
    }
    public HTTPResponse wrapResponse(){
        HTTPResponse httpResponse = new HTTPResponse();
        try {
            httpResponse.setHttpCode(Integer.parseInt(requestLine.split(" ")[1]));
        } catch(IllegalArgumentException ignored){}
        try {
            httpResponse.setReasonPhrase(requestLine.split(" ")[1].substring(1));
        } catch (StringIndexOutOfBoundsException e){e.printStackTrace();}
        httpResponse.setBody(body.toString());
        httpResponse.setHost(new Header("host", headers.get("Host")));
        httpResponse.setContentType(new Header("Content-Type", headers.get("Content-Type")));
        httpResponse.setContentLength(new Header("Content", headers.get("Content-Type")));
        return httpResponse;
    }
}