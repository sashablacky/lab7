package banana0081.lab6.client;

import banana0081.lab6.io.InputManagerImpl;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.exceptions.*;

import java.io.Serializable;
import java.util.Scanner;

public class HumanBeingFactory implements Serializable {

    private int id = 1;
    private Scanner in = new Scanner(System.in);
    InputManagerImpl inputManager = new InputManagerImpl(in);

public HumanBeing createHumanBeing() throws InvalidDataException {
    return new HumanBeing(getId(),
        inputManager.readName(),
        inputManager.readCoords(),
        inputManager.readRealHero(),
        inputManager.readHasToothpick(),
        inputManager.readImpactSpeed(),
        inputManager.readMinutesOfWaiting(),
        inputManager.readWeaponType(),
        inputManager.readMood(),
        inputManager.readCoolness(),
        inputManager.readCar());
}
public int getId() {
    return this.id;
}
public void setId(int id) {
        this.id = id++;
    }
}


