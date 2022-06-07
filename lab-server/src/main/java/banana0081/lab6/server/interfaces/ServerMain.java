package banana0081.lab6.server.interfaces;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String file = System.getenv("FILE_PATH");
        Server server = new Server(file);
        server.run(8080);
    }
}
