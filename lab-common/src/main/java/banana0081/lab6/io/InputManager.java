package banana0081.lab6.io;

import banana0081.lab6.commands.CommandWrapper;
import banana0081.lab6.data.*;
import banana0081.lab6.exceptions.*;

import java.util.Scanner;

public interface InputManager {
    /**
     * reads name from input
     * @return
     * @throws EmptyStringException
     */
    String readName() throws EmptyStringException;

    /**
     * reads x from input
     * @return
     * @throws InvalidNumberException
     */
    float readXCoord() throws InvalidNumberException;

    /**
     * reads y from input
     * @return
     * @throws InvalidNumberException
     */
    Long readYCoord() throws InvalidNumberException;

    /**
     * reads coordinates from input
     * @return
     * @throws InvalidNumberException
     */
    Coordinates readCoords() throws InvalidNumberException;

    /**
     * reads realHero from input
     * @return
     * @throws InvalidBooleanException
     */
    Boolean readRealHero() throws InvalidBooleanException;

    /**
     * reads hasToothpick from input
     * @return
     * @throws InvalidBooleanException
     */
    Boolean readHasToothpick() throws InvalidBooleanException;

    /**
     * reads impactSpeed from input
     * @return
     * @throws InvalidNumberException
     */
    float readImpactSpeed() throws InvalidNumberException;

    /**
     * reads minutesOfWaiting from input
     * @return
     * @throws InvalidNumberException
     */
    Long readMinutesOfWaiting() throws InvalidNumberException;

    /**
     * reads WeaponType from input
     * @return
     * @throws InvalidEnumException
     */
    WeaponType readWeaponType() throws InvalidEnumException;

    /**
     * reads Mood from input
     * @return
     * @throws InvalidDataException
     */
    Mood readMood() throws InvalidEnumException;

    /**
     * reads Car from input
     * @return
     * @throws InvalidDataException
     */
    Car readCar() throws InvalidDataException;

    /**
     * reads HumanBeing from input
     * @return
     * @throws InvalidDataException
     */
    HumanBeing readHumanBeing() throws InvalidDataException;

    /**
     * reads command-argument pair from input
     * @return
     */
    CommandWrapper readCommand();

    /**
     * gets input scanner
     * @return
     */
    Scanner getScanner();
}