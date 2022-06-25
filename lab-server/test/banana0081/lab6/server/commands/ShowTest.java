package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.*;
import banana0081.lab6.http.HTTPRequest;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ShowTest {

    HumanBeingCollectionManager collectionManager = new HumanBeingCollectionManager();

    @Test
    public void isDisplayed(){
        HumanBeing humanBeing1 = new HumanBeing();
        collectionManager.getCollection().add(humanBeing1);
        humanBeing1.setId(1);
        humanBeing1.setName("alex");
        humanBeing1.setCoordinates(new Coordinates(2, 2L));
        humanBeing1.setCreationDate(new Date());
        humanBeing1.setRealHero(true);
        humanBeing1.setHasToothpick(true);
        humanBeing1.setImpactSpeed(34);
        humanBeing1.setMinutesOfWaiting(20);
        humanBeing1.setWeaponType(WeaponType.AXE);
        humanBeing1.setMood(Mood.CALM);
        humanBeing1.setCar(new Car(true));
        humanBeing1.setMinutesOfWaiting(3);
        Show show = new Show(collectionManager);
        show.execute(new HTTPRequest());
        assertEquals(humanBeing1, humanBeing1);
    }
}