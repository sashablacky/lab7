package banana0081.lab6.server.commands;

import banana0081.lab6.data.HumanBeing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import banana0081.lab6.collection.HumanBeingCollectionManager;

import java.util.LinkedList;

class ClearTest {
        HumanBeingCollectionManager collectionManager;

    @Test
    public void isClearTest(){
        LinkedList<HumanBeing> humanBeing = collectionManager.getCollection();
        collectionManager.clear();
        Assertions.assertTrue(humanBeing.isEmpty());
    }
}