package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.server.interfaces.Command;

public class Exit implements Command {

    HumanBeingCollectionManager collectionManager;

    Exit(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
//        collectionManager.save;
        pack.pack("Вы откллючились от сервера\n");
        return pack;
    }

}
