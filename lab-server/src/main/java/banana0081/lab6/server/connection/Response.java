package banana0081.lab6.server.connection;

import banana0081.lab6.Pack;
import banana0081.lab6.http.HTTPResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Response {
    public static ByteBuffer serialize(HTTPResponse httpResponse) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(httpResponse.toString().getBytes(StandardCharsets.UTF_8));
        return ByteBuffer.wrap(baos.toByteArray());
    }
}
