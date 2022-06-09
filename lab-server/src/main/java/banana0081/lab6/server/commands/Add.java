package banana0081.lab6.server.commands;
import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.server.interfaces.Command;
public class Add implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Add(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public Pack execute(Pack pack) {
        collectionManager.add(pack.getHumanBeing());
        pack.pack("Человек добавлен\n");
        return pack;
    }

}
