package banana0081.lab6.server.connection;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.io.HumanBeingReader;
import banana0081.lab6.server.commands.CommandInvokerServer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class ClientReader {
    private HumanBeingCollectionManager collectionManager;
    private Scanner in = new Scanner(System.in);
    private HumanBeingReader HumanBeingReader = new HumanBeingReader(in);
    private String str = "";

    void reader(ServerSocketChannel serverSocket, HumanBeingCollectionManager collectionManager) throws IOException, ClassNotFoundException {
        this.collectionManager = collectionManager;
        Selector selector;
        selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(Integer.MAX_VALUE/2);

        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                if (!key.isValid()) continue;
                if (key.isAcceptable()) accept(selector, serverSocket);
                if (key.isReadable()) read(key, buffer);
            }
        }
    }


    public static void accept(Selector selector, ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel channel = serverSocketChannel.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
    }

    public void read(SelectionKey key, ByteBuffer buffer) throws IOException, ClassNotFoundException {
        Pack pack = new Pack();
        pack.pack("");
        Request request = new Request();
        SocketChannel client = (SocketChannel) key.channel();
        CommandInvokerServer commandInvoker = new CommandInvokerServer(collectionManager, in, HumanBeingReader, client);
        int num = client.read(buffer);
        if (num == -1) {
            client.close();
        } else {
            buffer.flip();
            pack = (Pack) request.deserialize(buffer);
            buffer.clear();
            pack = commandInvoker.execute(pack);
            if (!pack.getCommandName().equals("get_human_being")) {
                Response response = new Response();
                buffer.put(response.serialize(pack));
                buffer.flip();
                client.write(buffer);
                buffer.clear();
            }
        }
    }
}