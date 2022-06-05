package banana0081.lab6.io;
import banana0081.lab6.exceptions.*;

@FunctionalInterface
/**
 *user input callback
 */
public interface Askable<T>{
    T ask() throws InvalidDataException;
}