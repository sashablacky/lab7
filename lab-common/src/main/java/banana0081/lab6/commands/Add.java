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
        HumanBeingReader humanBeingReader = new HumanBeingReader();
        try {
            httpRequest.pack(nameCommand, humanBeingReader.readHumanBeing(), HttpMethod.PUT);
        } catch(InvalidDataException e){System.out.println(e.getMessage());}
        return httpRequest;
    }
}
