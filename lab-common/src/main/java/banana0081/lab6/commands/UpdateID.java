package banana0081.lab6.commands;


import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterfaceWithArgument;
import banana0081.lab6.connection.Request;
import banana0081.lab6.connection.Response;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.exceptions.InvalidDataException;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.http.HttpMethod;
import banana0081.lab6.io.HumanBeingReader;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class UpdateID implements CommandInterfaceWithArgument {
    private String[] arg;
    private final Request request = new Request();
    private final Response response = new Response();
    private Socket socket = new Socket();
    private HTTPResponse pack2 = new HTTPResponse();
    UpdateID(Socket socket) {
        this.socket = socket;
    }
    Scanner sc = new Scanner(System.in);
    @Override
    public HTTPRequest execute(String nameCommand, HTTPRequest pack) {
        try {
            pack.pack(nameCommand, arg, HttpMethod.PUT);
            request.request(pack, socket);
            pack2 = response.responseUpdate(socket);
            if (pack2.getHttpCode()==200 && pack2.getHumanBeing() != null) {
                print(pack2.getHumanBeing());
                HumanBeing newHumanBeing = new HumanBeing();
                HumanBeingReader humanBeingReader = new HumanBeingReader();
                try {
                    newHumanBeing = humanBeingReader.readHumanBeing();
                    newHumanBeing.setCreator(pack.getAuthorization());
                } catch(InvalidDataException e){System.out.println(e.getMessage());}
                newHumanBeing.setId(pack.getIntegerArgument());
                pack.pack("update_by_id", newHumanBeing, HttpMethod.PUT);
                request.request(pack, socket);
            } else {
                print(pack2.getHttpCode() + " " + pack2.getReasonPhrase());
                return null;
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