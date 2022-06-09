package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.io.HumanBeingReader;
import banana0081.lab6.server.interfaces.Command;
import banana0081.lab6.server.interfaces.CommandWithArguments;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class CommandInvokerServer {
    Scanner in;
    private HashMap<String, Command> commandWithoutArguments;
    private HashMap<String, CommandWithArguments> commandWithArguments;
    private HumanBeingCollectionManager collectionManager;
    HumanBeingReader humanBeingReader;
    SocketChannel client;

    public CommandInvokerServer(HumanBeingCollectionManager collectionManager, Scanner in, HumanBeingReader humanBeingReader, SocketChannel client) {
        this.collectionManager = collectionManager;
        this.in = in;
        this.humanBeingReader = humanBeingReader;
        commandWithoutArguments = new HashMap<>();
        commandWithArguments = new HashMap<>();
        this.client = client;
        this.registerCommands();
    }


    private void registerCommands() {
        register("help", new Help());//
        register("info", new Info(collectionManager));//
        register("show", new Show(collectionManager));//
        register("add", new Add(collectionManager));//
        registerWithArgument("update", new Update(collectionManager));//
        registerWithArgument("remove_by_id", new RemoveById(collectionManager, in));//
        register("clear", new Clear(collectionManager));//
        registerWithArgument("execute_script", new ExecuteScript());
        register("exit", new Exit(collectionManager));//
        register("shuffle", new Shuffle(collectionManager));//
    }

    private void register(String name, Command command) {
        commandWithoutArguments.put(name, command);
    }

    private void registerWithArgument(String name, CommandWithArguments command) {
        commandWithArguments.put(name, command);
    }

    public Pack execute(Pack pack) {
        try {
            String nameCommand = pack.getCommandName();
            String[] args = pack.getArg();
            Pack response = new Pack();
            if (commandWithArguments.containsKey(nameCommand.toLowerCase(Locale.ROOT))) {
                CommandWithArguments command;
                command = commandWithArguments.get(nameCommand.toLowerCase(Locale.ROOT));
                command.getArgs(args);
                response = command.execute(pack);
            } else if (commandWithoutArguments.containsKey(nameCommand.toLowerCase(Locale.ROOT))) {
                Command command;
                command = commandWithoutArguments.get(nameCommand.toLowerCase(Locale.ROOT));
                response = command.execute(pack);
            }
            return response;
        } catch (NullPointerException e) {
            Pack response = new Pack();
            response.pack("Сервер не получил команду");
            return response;
        }
    }
}
