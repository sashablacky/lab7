package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.io.HumanBeingReader;
import banana0081.lab6.abstraction.CommandInterface;
import banana0081.lab6.exceptions.InvalidDataException;

import java.util.Scanner;

public class RemoveLast implements CommandInterface {
    private Object HumanBeingReader;
    private Scanner sc;
    @Override
    public Pack execute(String nameCommand, Pack pack) {
        HumanBeingReader humanBeingReader = new HumanBeingReader(sc);
        try {
            pack.pack(nameCommand, humanBeingReader.readHumanBeing());
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        return pack;
    }
}
