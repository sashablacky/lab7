package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.server.interfaces.Command;

public class Info implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Info(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        pack.pack(collectionManager.getInfo());
        return pack;
    }

}