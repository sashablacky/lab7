package banana0081.lab6.connection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Request {
    public void request(Object obj, Socket socket) throws IOException {
        socket.getOutputStream().write(obj.toString().getBytes(StandardCharsets.UTF_8));
        socket.getOutputStream().flush();
    }

    public ByteArrayOutputStream serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        return byteArrayOutputStream;
    }
}
