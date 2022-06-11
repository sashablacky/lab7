package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.server.interfaces.Command;

public class Clear implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Clear(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.clear();
        if (collectionManager.getCollection().isEmpty()){
            pack.pack("Collection was cleared!\n");
        } else{
            pack.pack("Collection was not cleared\n");
        }
        return pack;
    }
}
