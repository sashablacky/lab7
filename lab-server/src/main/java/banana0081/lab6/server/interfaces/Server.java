package banana0081.lab6.server.interfaces;

import banana0081.lab6.abstraction.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    private String file;
    HumanBeingCollectionManager collectionManager;

    public Server(String file) {
        this.file = file; this.collectionManager = new HumanBeingCollectionManager(file);
    }

    public void run(int port) {
        ServerSocketChannel serverSocket;
        FileWorker fileWorker = new FileWorker(collectionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            File file = new File(System.getenv("FILE_PATH"));
            try (FileOutputStream outputStream = new FileOutputStream(file)){
                for (HumanBeing vals : collectionManager.getCollection()) {
                    outputStream.write(vals.toString().getBytes(StandardCharsets.UTF_8));
                    outputStream.write("\n".getBytes(StandardCharsets.UTF_8));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        try {
            try {
                String inputFile = System.getenv("FILE_PATH");
                File file = new File(inputFile);
                if (!file.canWrite() || !file.isFile() || file.isDirectory()) throw new IOException();
                fileWorker.fromCSVtoObj();
                if (collectionManager.getSize() == 0) {
                    System.out.println("Добавьте объекты с помощью команды add, после чего введите команду save для сохранения в csv!");
                } else System.out.println("Объекты из файла загружены!");

            } catch (IOException e) {
                System.out.println(("Такого файла нет"));
                System.exit(0);
            }
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            ClientReader clientReader = new ClientReader();
            clientReader.reader(serverSocket, collectionManager);
        } catch (IOException e) {
            IOUtils.println("Ошибка ввода/вывода!");
        } catch (ClassNotFoundException e) {
            IOUtils.println("Сериализуемый класс не наследует класс Serializable");
        }
    }
}