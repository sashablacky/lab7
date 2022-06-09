package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.server.interfaces.Command;

public class Clear implements Command {
    private HumanBeingCollectionManager collectionManager;

    public Clear(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        String response = "";
        collectionManager.clear();
        pack.pack("Collection was cleared!\n");
        return pack;
    }
}
