package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class ShuffleTest {

    HumanBeingCollectionManager collectionManager = new HumanBeingCollectionManager();
    private Shuffle shuffle;

    @Test
    public void isDifferent(){
        LinkedList<HumanBeing> humanBeings1;
        humanBeings1=collectionManager.getCollection();
        Shuffle shuffle = new Shuffle(collectionManager);
        shuffle.execute(new Pack());
        LinkedList<HumanBeing> humanBeings2;
        humanBeings2=collectionManager.getCollection();
        boolean areDifferent = humanBeings1.contains(humanBeings2);
        assertFalse(areDifferent);
    }
}