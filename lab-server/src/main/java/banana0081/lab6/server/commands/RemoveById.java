package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.server.interfaces.CommandWithArguments;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RemoveById implements CommandWithArguments {
    private HumanBeingCollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    RemoveById (HumanBeingCollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public Pack execute(Pack pack) {
        for (String argument : arguments) {
            collectionManager.removeByID(Integer.parseInt(argument));
        }
        pack.pack("Object was deleted.");
        return pack;
    }
    @Override
    public void getArgs(String[] arguments) {
        this.arguments = arguments;
    }
}
