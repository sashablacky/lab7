package banana0081.lab6.client;

import banana0081.lab6.Pack;
import banana0081.lab6.commands.CommandInvoker;
import banana0081.lab6.connection.Request;
import banana0081.lab6.connection.Response;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HttpMethod;
import banana0081.lab6.io.InputManagerImpl;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class Client {
    private final HTTPRequest pack = new HTTPRequest();
    private final Response response = new Response();
    private final Request request = new Request();
    private final Scanner in = new Scanner(System.in);
    InputManagerImpl inputManager = new InputManagerImpl(in);

    Scanner sc = new Scanner(System.in);
    public void run(int port) {
        InetAddress address;
        try {
            address = Inet4Address.getByName("localhost");
            while (true) {
                Socket socket = new Socket(address, port);
                CommandInvoker commandInvoker = new CommandInvoker(socket);
                print("Введите команду: ");
                String str = sc.nextLine();
                if (commandInvoker.execute(str, pack) & !str.startsWith("update")) {
                    request.request(pack, socket);
                    if (str.trim().equalsIgnoreCase("show"))
                        response.responseShow(socket);
                    else
                        response.response(socket);
                    if (str.trim().equalsIgnoreCase("exit"))
                        System.exit(0);
                }
            }
        } catch (IOException e) {
            printErr("Повторная попытка подключения через 5 секунд!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            run(port);
        } catch (ClassNotFoundException e) {
            printErr("Сериализуемый класс не наследует класс Serializable");
        }
    }
}