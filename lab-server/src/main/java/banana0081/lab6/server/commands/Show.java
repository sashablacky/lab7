package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.server.interfaces.Command;

public class Show implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Show(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        String s = "";
        if(collectionManager.getSize()>0) {
            for (HumanBeing humanBeing : collectionManager.getCollection()) {
                s += (humanBeing.toString()) + '\n';
            }
        }else{s="Collection is empty";}
        pack.pack(s);
        return pack;
    }
}