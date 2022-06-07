package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterfaceWithArgument;
import banana0081.lab6.connection.Request;
import banana0081.lab6.connection.Response;
import banana0081.lab6.io.HumanBeingReader;

import java.io.File;
import java.net.Socket;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class ExecuteScript implements CommandInterfaceWithArgument {
    private String[] arg;
    Set<String> scriptsInProcess = new HashSet<>();
    CommandInvoker commandInvoker;
    Pack pack1 = new Pack();
    Request request = new Request();
    Response response = new Response();
    private Socket socket;

    ExecuteScript(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Pack execute(String nameCommand, Pack pack) {
        String fileName = arg[0];
        File file = new File(fileName);
        String absolutePath = file.getAbsolutePath();
        commandInvoker = new CommandInvoker(socket);

        if (scriptsInProcess.contains(absolutePath)) {
            printErr("Данный скрипт уже выполняется!");
            return null;
        }
        scriptsInProcess.add(absolutePath);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            HumanBeingReader.setScanner(scanner);
            print("Попытка прочитать команды из файла " + file.getName());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                print("Команда " + line);
                commandInvoker.execute(line, pack1);
                request.request(pack1, socket);
                response.response(socket);
            }
            System.out.println("Все команды были успешно выполнены.");
            scanner.close();
        } catch (Exception e) {
            printErr("Непредвиденная ошибка при выполнении скрипта!");
        }
        scriptsInProcess.remove(absolutePath);
        HumanBeingReader.setScanner(new Scanner(System.in));
        pack.pack(nameCommand, arg);
        return pack;
    }

    @Override
    public void getArg(String[] arg) {
        this.arg = arg;
    }
}
