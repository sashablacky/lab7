package banana0081.lab6.connection;

import banana0081.lab6.Pack;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class Response {
    Pack pack = new Pack();

    public void response(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        socket.getInputStream().read(bytes);
        pack = deserialize(bytes);
        print(pack.getCommandName());
    }

    public void responseShow(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        InputStream inputStream = socket.getInputStream();
        socket.shutdownOutput();
        while (inputStream.read(bytes) > -1) {
            pack = deserialize(bytes);
            print(pack.getCommandName());
        }
    }

    public Pack responseUpdate(Socket socket) throws IOException, ClassNotFoundException {
        byte[] bytes = new byte[32768];
        socket.getInputStream().read(bytes);
        pack = deserialize(bytes);
        if (!pack.getCommandName().equals(""))
            printErr(pack.getCommandName());
        return pack;
    }

    public Pack deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        return (Pack) objectInputStream.readObject();
    }
}