package banana0081.lab6.commands;

import banana0081.lab6.exceptions.CommandException;
import banana0081.lab6.exceptions.InvalidDataException;

@FunctionalInterface
/**
 * Command callback interface
 */
public interface Command {
    void run(String arg) throws CommandException, InvalidDataException;
}
