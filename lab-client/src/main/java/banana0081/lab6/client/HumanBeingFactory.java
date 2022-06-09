package banana0081.lab6.client;

import banana0081.lab6.io.ConsoleInputManager;
import banana0081.lab6.io.InputManagerImpl;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.exceptions.*;

import java.io.Serializable;
import java.util.Scanner;

public class HumanBeingFactory implements Serializable {

    private int id = 1;
    private Scanner in = new Scanner(System.in);
    ConsoleInputManager inputManager = new ConsoleInputManager();

public HumanBeing createHumanBeing() throws InvalidDataException {
    return inputManager.readHumanBeing();
}
public int getId() {
    return this.id;
}
public void setId(int id) {
        this.id = id++;
    }
}


