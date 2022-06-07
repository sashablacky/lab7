package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterface;
import banana0081.lab6.abstraction.CommandInterfaceWithArgument;

import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static banana0081.lab6.io.OutputManager.printErr;

public class CommandInvoker {
    private Socket socket;
    public CommandInvoker(Socket socket) {
        this.socket = socket;
        this.commandRegister();
    }

    Map<String, CommandInterface> commandWithoutArg = new HashMap<>();
    Map<String, CommandInterfaceWithArgument> commandWithArgument = new HashMap<>();

    public void commandRegister() {
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

    public void register(String nameCommand, CommandInterface command) {
        commandWithoutArg.put(nameCommand, command);
    }

    public void registerWithArg(String nameCommand, CommandInterfaceWithArgument commandWithArg) {
        commandWithArgument.put(nameCommand, commandWithArg);
    }

    public boolean execute(String str, Pack pack) {
        String[] word = str.trim().split(" ");
        String[] arg = Arrays.copyOfRange(word, 1, word.length);

        if (commandWithArgument.containsKey(word[0].toLowerCase(Locale.ROOT))) {
            if (arg.length == 1) {
                CommandInterfaceWithArgument command;
                command = commandWithArgument.get(word[0].toLowerCase(Locale.ROOT));
                command.getArg(arg);
                command.execute(word[0], pack);
                return true;
            } else {
                printErr("У команды " + word[0] + " должен быть один аргумент!");
                return false;
            }
        } else if (commandWithoutArg.containsKey(word[0].toLowerCase(Locale.ROOT))) {
            if (arg.length <= 0) {
                CommandInterface command;
                command = commandWithoutArg.get(word[0].toLowerCase(Locale.ROOT));
                command.execute(word[0], pack);
                return true;
            } else {
                printErr("У команды " + word[0] + " не должно быть аргументов!");
                return false;
            }
        } else {
            printErr("Комманда " + word[0] + " не распознана, введите корректную команду!");
            return false;
        }
    }
}
