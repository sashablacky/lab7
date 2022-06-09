package banana0081.lab6.server;

import banana0081.lab6.server.connection.Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = System.getenv("FILE_PATH");
        Server server = new Server(file);
        server.run(8080);
    }
}
