package banana0081.lab6.commands;


import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterfaceWithArgument;
import banana0081.lab6.connection.Request;
import banana0081.lab6.connection.Response;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.http.HttpMethod;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class UpdateID implements CommandInterfaceWithArgument {
    private String[] arg;
    private final Request request = new Request();
    private final Response response = new Response();
    private HTTPRequest pack1 = new HTTPRequest();
    private Socket socket = new Socket();
    private HTTPResponse pack2 = new HTTPResponse();
    UpdateID(Socket socket) {
        this.socket = socket;
    }
    Scanner sc = new Scanner(System.in);
    @Override
    public HTTPRequest execute(String nameCommand, HTTPRequest pack) {
        try {
            pack1.pack(nameCommand, arg, HttpMethod.POST);
            request.request(pack1, socket);
            pack2 = response.responseUpdate(socket);
            if (pack2.getHumanBeing() != null) {
                print("Введите stop, когда захотите прервать изменение элемента коллекции!");
                print("Введите значения для изменения ID: ");
                String[] commandWords = new String[]{""};
                while (!commandWords[0].equals("stop")) {
                    try {
                        String str = sc.nextLine();
                        if (str.equals(""))
                            continue;
                        commandWords = new String[0];
                        commandWords = str.trim().split(" ");
                        if (commandWords.length == 2) {
                            updateById(commandWords[0], pack2.getHumanBeing(), Integer.parseInt(commandWords[1]));
                        } else if (commandWords[0].equals("stop")) {
                            print("Человек был изменён!");
                        } else {
                            printErr("Не указан аргумент или было указано более двух аргументов!");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Не указано поле или значение!");
                    }
                }
                pack1.pack("get_human_being", pack2.getHumanBeing(), HttpMethod.GET);
                request.request(pack2, socket);
            } else {
                pack.pack(pack2.getCommand(), HttpMethod.POST);
            }
            return pack;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateById(String command, HumanBeing humanBeing, int arg) {humanBeing.setId(arg);
    }

    @Override
    public void getArg(String[] arg) {
        this.arg = arg;
    }
}