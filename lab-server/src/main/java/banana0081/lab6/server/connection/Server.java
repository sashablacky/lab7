package banana0081.lab6.server.connection;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.io.IOUtils;
import banana0081.lab6.server.FileWorker;
import banana0081.lab6.server.commands.Save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.Map;

public class Server {
    private Connection conn;
    HumanBeingCollectionManager collectionManager;

    public Server(Connection conn) {
        this.conn = conn; this.collectionManager = new HumanBeingCollectionManager(conn);
    }

    public void run(int port) {
        ServerSocketChannel serverSocket;
        FileWorker fileWorker = new FileWorker(collectionManager);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try{File file = new File(System.getenv("FILE_PATH"));
            file.createNewFile();}
            catch (NullPointerException | IOException e) {
                e.printStackTrace();
            }
            try{
                Save save = new Save(collectionManager, conn);
                HTTPRequest httpRequest = new HTTPRequest();
                save.execute(httpRequest);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }));
        try {
            try {
                String inputFile = System.getenv("FILE_PATH");
                File file = new File(inputFile);
                if (!file.canWrite() || !file.isFile() || file.isDirectory()) throw new IOException();
                LinkedList<HumanBeing> loadedCollection = fileWorker.load();
                collectionManager.load(loadedCollection);
                if (collectionManager.getSize() == 0) {
                    System.out.println("Добавьте объекты с помощью команды add, после чего введите команду save для сохранения в xml!");
                } else System.out.println("Объекты из файла загружены! Добавьте объекты с помощью команды add, после чего введите команду save для сохранения в xml.");

            } catch (IOException | NullPointerException e) {
                System.out.println(("Такого файла нет"));
                System.exit(0);
            }
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            ClientReader clientReader = new ClientReader();
            clientReader.reader(serverSocket, collectionManager, conn);
        } catch (IOException e) {
            IOUtils.println("Ошибка ввода/вывода!");
        } catch (ClassNotFoundException e) {
            IOUtils.println("Сериализуемый класс не наследует класс Serializable");
        }
    }
}