package banana0081.lab6.server.interfaces;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.HumanBeingCollectionManager;
import banana0081.lab6.commands.Add;
import banana0081.lab6.commands.Command;
import banana0081.lab6.commands.CommandWithoutArg;
import banana0081.lab6.io.HumanBeingReader;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class CommandInvokerServer {
    Scanner in;
    private HashMap<String, Command> commandWithoutArguments;
    private HashMap<String, CommandInterfaceServerWithArgument> commandWithArguments;
    private HumanBeingCollectionManager collectionManager;
    HumanBeingReader HumanBeingReader;
    SocketChannel client;

    public CommandInvokerServer(HumanBeingCollectionManager collectionManager, Scanner in, HumanBeingReader HumanBeingReader, SocketChannel client) {
        this.collectionManager = collectionManager;
        this.in = in;
        this.HumanBeingReader = HumanBeingReader;
        commandWithoutArguments = new HashMap<>();
        commandWithArguments = new HashMap<>();
        this.client = client;
        this.registerCommands();
    }


    public void registerCommands() {
        register("help", new CommandWithoutArg());
        register("info", new CommandWithoutArg());
        register("show", new CommandWithoutArg());
        register("clear", new CommandWithoutArg());
        register("exit", new CommandWithoutArg());
        register("shuffle", new CommandWithoutArg());
        register("sum_of_minutes_of_waiting", new CommandWithoutArg());
        register("min_by_minutes_of_waiting", new CommandWithoutArg());
        register("print_unique_impact_speed", new CommandWithoutArg());
        register("add", new Add());
        register("remove_first", new RemoveFirst());
        register("remove_last", new RemoveLast());
        registerWithArg("update_id", new UpdateID(socket));
        registerWithArg("remove_by_id", new RemoveById());
        registerWithArg("execute_script", new ExecuteScript(socket));

    }

    private void register(String name, Command command) {
        commandWithoutArguments.put(name, command);
    }
    private void registerWithArg(String name, CommandInterfaceServerWithArgument command) {
        commandWithArguments.put(name, command);
    }

    public Pack execute(Pack pack) {
        String nameCommand = pack.getCommandName();
        String[] args = pack.getArg();
        Pack response = new Pack();
        if (commandWithArguments.containsKey(nameCommand.toLowerCase(Locale.ROOT))) {
            CommandInterfaceServerWithArgument command;
            command = commandWithArguments.get(nameCommand.toLowerCase(Locale.ROOT));
            command.getArg(args);
            response = command.execute(pack);
        } else if (commandWithoutArguments.containsKey(nameCommand.toLowerCase(Locale.ROOT))) {
            CommandInterfaceServer command;
            command = (CommandInterfaceServer) commandWithoutArguments.get(nameCommand.toLowerCase(Locale.ROOT));
            response = command.execute(pack);
        }
        return response;
    }
}
