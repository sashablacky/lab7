package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.io.HumanBeingReader;
import banana0081.lab6.server.interfaces.Command;
import banana0081.lab6.server.interfaces.CommandWithArguments;

import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class CommandInvokerServer {
    Scanner in;
    private final HashMap<String, Command> commandWithoutArguments;
    private final HashMap<String, CommandWithArguments> commandWithArguments;
    private final HumanBeingCollectionManager collectionManager;
    HumanBeingReader humanBeingReader;
    SocketChannel client;
    Connection conn;

    public CommandInvokerServer(HumanBeingCollectionManager collectionManager, Scanner in, HumanBeingReader humanBeingReader, SocketChannel client, Connection conn) {
        this.collectionManager = collectionManager;
        this.in = in;
        this.humanBeingReader = humanBeingReader;
        commandWithoutArguments = new HashMap<>();
        commandWithArguments = new HashMap<>();
        this.client = client;
        this.conn = conn;
        this.registerCommands();
    }


    private void registerCommands() {
        register("help", new Help());//
        register("info", new Info(collectionManager));//
        register("show", new Show(collectionManager));//
        register("add", new Add(collectionManager));//
        registerWithArgument("update", new Update(collectionManager));//
        registerWithArgument("update_by_id", new UpdateById(collectionManager));//
        registerWithArgument("remove_by_id", new RemoveById(collectionManager, in));//
        register("clear", new Clear(collectionManager));//
        registerWithArgument("execute_script", new ExecuteScript());
        register("exit", new Exit(collectionManager));//
        register("shuffle", new Shuffle(collectionManager));//
        register("sum_of_minutes_of_waiting", new SumOfMinutesOfWaiting(collectionManager));
        register("print_unique_impact_speed", new PrintUniqueImpactSpeed(collectionManager));
        register("min_by_minutes_of_waiting", new MinByMinutesOfWaiting(collectionManager));
    }

    private void register(String name, Command command) {
        commandWithoutArguments.put(name, command);
    }

    private void registerWithArgument(String name, CommandWithArguments command) {
        commandWithArguments.put(name, command);
    }

    public HTTPResponse execute(HTTPRequest httpRequest) {
        try {
            String nameCommand = httpRequest.getCommand();
            String[] args = httpRequest.getBody().split("\n");
            HTTPResponse response = new HTTPResponse();
            if (commandWithArguments.containsKey(nameCommand.toLowerCase(Locale.ROOT))) {
                CommandWithArguments command;
                command = commandWithArguments.get(nameCommand.toLowerCase(Locale.ROOT));
                command.getArgs(args);
                response = command.execute(httpRequest);
            } else if (commandWithoutArguments.containsKey(nameCommand.toLowerCase(Locale.ROOT))) {
                Command command;
                command = commandWithoutArguments.get(nameCommand.toLowerCase(Locale.ROOT));
                response = command.execute(httpRequest);
            } else {
                response.pack(new String[]{"Команда не найдена!"}, 404, "Not Found");
            }
            return response;
        } catch (NullPointerException e) {
            HTTPResponse response = new HTTPResponse();
            response.setHttpCode(400);
            response.setReasonPhrase("Сервер не получил команду или такой команды не существует");
            return response;
        }
    }
}
