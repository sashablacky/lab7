package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumOfMinutesOfWaitingTest {
    HumanBeingCollectionManager collectionManager = new HumanBeingCollectionManager();
    private SumOfMinutesOfWaiting SumOfMinutes;

    @Test
    void actuallySum() {
        HumanBeing humanBeing1 = new HumanBeing();
        collectionManager.getCollection().add(humanBeing1);
        humanBeing1.setMinutesOfWaiting(3);
        HumanBeing humanBeing2 = new HumanBeing();
        collectionManager.getCollection().add(humanBeing2);
        humanBeing2.setMinutesOfWaiting(5);
        SumOfMinutes = new SumOfMinutesOfWaiting(collectionManager);
        SumOfMinutes.execute(new Pack());
        assertEquals(8, SumOfMinutes.getResult());
    }
}