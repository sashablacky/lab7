package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.server.interfaces.CommandWithArguments;

import java.util.Scanner;

public class Update implements CommandWithArguments {
    private String[] arguments;
    private HumanBeingCollectionManager collectionManager;
    private Scanner in;

    public Update(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        if (collectionManager.getElementById(Integer.parseInt(arguments[0])) != null) {
            pack.pack("", collectionManager.getElementById(Integer.parseInt(arguments[0])));
        }
        else
            pack.pack("Элемента с данным id нет в коллекции!");
        return pack;
    }

    @Override
    public void getArgs(String[] arguments) {
        this.arguments = arguments;
    }
}