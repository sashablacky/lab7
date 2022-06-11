package banana0081.lab6.server.commands;

import banana0081.lab6.abstraction.CollectionManager;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.server.FileWorker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class SaveTest {
    HumanBeingCollectionManager collectionManager;
    CollectionManager collection;
    FileWorker worker;
    @Test
    public void isSaved(){
        LinkedList<HumanBeing> oldHumanBeing = collectionManager.getCollection();
        new Save(collection);
        worker.load();
        Assertions.assertNotSame(oldHumanBeing, collectionManager.getCollection());
    }
}