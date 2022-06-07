package banana0081.lab6.commands;

import banana0081.lab6.abstraction.CollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.exceptions.*;
import banana0081.lab6.file.FileInterface;
import banana0081.lab6.io.ConsoleInputManager;
import banana0081.lab6.io.FileInputManager;
import banana0081.lab6.io.InputManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class CommandManager implements Commandable{
    private final Map<String,Command> map;
    private final CollectionManager<HumanBeing> collectionManager;
    private InputManager inputManager;
    private final FileInterface fileManager;
    private boolean isRunning;
    private String currentScriptFileName;

    private static final Stack<String> callStack = new Stack<>();

    public void clearStack(){
        callStack.clear();
    }
    public CommandManager(CollectionManager<HumanBeing> cManager, InputManager iManager, FileInterface fManager){

        isRunning = false;
        this.inputManager = iManager;
        this.collectionManager = cManager;
        this.fileManager = fManager;

        currentScriptFileName = "";
        map = new HashMap<String,Command>();
        addCommand("info", (a)->print(collectionManager.getInfo()));
        addCommand("help", (a)->print(getHelp()));
        addCommand("show", (a)->{
            if (collectionManager.getCollection().isEmpty()) print("Collection is empty");
            else for (HumanBeing humanBeing: collectionManager.getCollection()){
                print(humanBeing.toString());
                }
            });
        addCommand("add", (a)-> collectionManager.add(inputManager.readHumanBeing()));
        addCommand("update", (arg)->{
            int id = 0;
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            }
            try{
                id = Integer.parseInt(arg);
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentException("Id must be an integer");
            }
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("Element with this id does not exist");
            
            collectionManager.updateByID(id, inputManager.readHumanBeing());
            
        });
        addCommand("remove_by_id", (arg)->{
            int id = 0;
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            }
            try{
                id = Integer.parseInt(arg);
            } catch (NumberFormatException e){
                throw new InvalidCommandArgumentException("Id must be an integer");
            }
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            if (!collectionManager.checkID(id)) throw new InvalidCommandArgumentException("Element with this id does not exist");
            
            collectionManager.removeByID(id);
            
        });
        addCommand("clear", (a)-> collectionManager.clear());
        addCommand("save", (arg)->{
            if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
            if (collectionManager.getCollection().isEmpty()) print("Collection is empty, nothing to save");
            if(!fileManager.write(collectionManager.getCollection())) throw new CommandException("Cannot save collection");
        });
        addCommand("load", (arg)->{
            if (!(arg == null ||arg.equals(""))) fileManager.setPath(arg);
            LinkedList<HumanBeing> data = fileManager.load();
            if(data.equals("")) throw new CommandException("cannot load, data not found");
            collectionManager.load(data);
            if (collectionManager.getSize() != 0){
            print("collection successfully loaded");}
            else{
                print("nothing to load");
            }
        });
        addCommand("execute_script",(arg)->{
            if (arg == null || arg.equals("")){
                throw new MissedCommandArgumentException();
            } 
            
            if (callStack.contains(currentScriptFileName)) throw new RecursiveScriptExecuteException();

            callStack.push(currentScriptFileName);
            CommandManager process = new CommandManager(collectionManager, inputManager, fileManager);
            process.fileMode(arg);
            callStack.pop();
            print("Successfully executed script " + arg);
            
        });
        addCommand("exit", (a)->isRunning=false);
        addCommand("remove_first", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.removeFirst();
        });
        addCommand("remove_last", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.removeLast();
        });
        addCommand("shuffle", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.shuffle();
        });
        addCommand("sum_of_minutes_of_waiting", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.sum_of_minutes_of_waiting();
        });
        addCommand("min_by_minutes_of_waiting", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.min_by_minutes_of_waiting();
        });
        addCommand("print_unique_impact_speed", (a)->{
            if (collectionManager.getCollection().isEmpty()) throw new EmptyCollectionException();
            collectionManager.print_unique_impact_speed();
        });

    }

    public void addCommand(String key, Command c){
        map.put(key, c);
    }

    public void runCommand(String s, String arg){
        try{
            if (! hasCommand(s)) throw new NoSuchCommandException();
            map.get(s).run(arg);
        } 
        catch(CommandException|InvalidDataException e){
            printErr(e.getMessage());
        }
    }
    public void runCommand(String s){
        runCommand(s,null);
    }
    public boolean hasCommand(String s){
        return map.containsKey(s);
    }
    public void consoleMode(){
        inputManager = new ConsoleInputManager();
        isRunning = true;
        while(isRunning){
            System.out.print("enter command (help to get command list): ");
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }
    public void fileMode(String path) {
        currentScriptFileName = path;
        isRunning = true;
        inputManager = new FileInputManager(path);
        isRunning = true;
        while(isRunning && inputManager.getScanner().hasNextLine()){
            CommandWrapper pair = inputManager.readCommand();
            runCommand(pair.getCommand(), pair.getArg());
        }
    }

    public static String getHelp(){ 
        return String.join("\n", "help : вывести справку по доступным командам",
        "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)",
        "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении",
        "add {element} : добавить новый элемент в коллекцию",
        "update id {element} : обновить значение элемента коллекции, id которого равен заданному",
        "remove_by_id id : удалить элемент из коллекции по его id",
        "clear : очистить коллекцию",
        "save : сохранить коллекцию в файл",
        "load : прочитать коллекцию из файла",
        "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.",
        "exit : завершить программу (без сохранения в файл)",
        "remove_first : удалить первый элемент из коллекции",
        "remove_last : удалить последний элемент из коллекции",
        "shuffle : перемешать элементы коллекции в случайном порядке",
        "sum_of_minutes_of_waiting : вывести сумму значений поля minutesOfWaiting для всех элементов коллекции",
        "min_by_minutes_of_waiting : вывести любой объект из коллекции, значение поля minutesOfWaiting которого является минимальным",
        "print_unique_impact_speed : вывести уникальные значения поля impactSpeed всех элементов в коллекции)");
    }
}
