package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.http.HTTPRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrintUniqueImpactSpeedTest {

    HumanBeingCollectionManager collectionManager = new HumanBeingCollectionManager();
    private PrintUniqueImpactSpeed uniqueImpactSpeed;

    @Test
    void actuallyUnique() {
        HumanBeing humanBeing1 = new HumanBeing();
        collectionManager.getCollection().add(humanBeing1);
        humanBeing1.setImpactSpeed(3);
        HumanBeing humanBeing2 = new HumanBeing();
        collectionManager.getCollection().add(humanBeing2);
        humanBeing2.setImpactSpeed(5);
        uniqueImpactSpeed =  new PrintUniqueImpactSpeed(collectionManager);
        uniqueImpactSpeed.execute(new HTTPRequest());
        assertEquals("Уникальные значения impactSpeed\n3.0\n5.0\n", uniqueImpactSpeed.getResponse());
    }

}