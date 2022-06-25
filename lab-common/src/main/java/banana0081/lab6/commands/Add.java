package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterface;
import banana0081.lab6.exceptions.InvalidDataException;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HttpMethod;
import banana0081.lab6.io.ConsoleInputManager;
import banana0081.lab6.io.HumanBeingReader;

import java.util.Scanner;

public class Add implements CommandInterface {


    @Override
    public HTTPRequest execute(String nameCommand, HTTPRequest httpRequest) {
        ConsoleInputManager humanBeingReader = new ConsoleInputManager();
            httpRequest.pack(nameCommand, humanBeingReader.readHumanBeing(), HttpMethod.POST);
        return httpRequest;
    }
}
