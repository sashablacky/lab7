package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.server.interfaces.Command;

import java.util.LinkedList;

public class Shuffle implements Command {

    HumanBeingCollectionManager collectionManager;


    Shuffle(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        LinkedList<HumanBeing> oldHuman = collectionManager.getCollection();
        collectionManager.shuffle();
        if (oldHuman.equals(collectionManager.getCollection())){
            pack.pack("Collection was shuffled\n");
        }else {
            pack.pack("Collection was not shuffled\n");
        }
        return pack;
    }
}
