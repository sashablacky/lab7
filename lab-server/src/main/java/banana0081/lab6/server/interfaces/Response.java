package banana0081.lab6.server.interfaces;

import banana0081.lab6.Pack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class Response {
    public ByteBuffer serialize(Pack pack) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(pack);
        return ByteBuffer.wrap(baos.toByteArray());
    }
}
