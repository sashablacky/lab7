package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterface;
import banana0081.lab6.exceptions.InvalidDataException;
import banana0081.lab6.io.ConsoleInputManager;
import banana0081.lab6.io.HumanBeingReader;

import java.util.Scanner;

public class Add implements CommandInterface {


    @Override
    public Pack execute(String nameCommand, Pack pack) {
        ConsoleInputManager humanBeingReader = new ConsoleInputManager();
            pack.pack(nameCommand, humanBeingReader.readHumanBeing());
        return pack;
    }
}
