package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.server.interfaces.Command;

public class Shuffle implements Command {

    HumanBeingCollectionManager collectionManager;

    Shuffle(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.shuffle();
        pack.pack("Collection was shuffled\n");
        return pack;
    }
}
