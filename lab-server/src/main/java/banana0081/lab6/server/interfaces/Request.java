package banana0081.lab6.server.interfaces;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

public class Request {
    public Object deserialize(ByteBuffer buffer) throws IOException, ClassNotFoundException {
        byte[] array = new byte[buffer.remaining()];
        buffer.get(array);
        ByteArrayInputStream bais = new ByteArrayInputStream(array);
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        return objectInputStream.readObject();
    }
}
