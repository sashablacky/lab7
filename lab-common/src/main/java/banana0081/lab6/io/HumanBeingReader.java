package banana0081.lab6.io;

import banana0081.lab6.commands.CommandWrapper;
import banana0081.lab6.data.*;
import banana0081.lab6.exceptions.*;

import java.util.Scanner;

/**
 * basic implementation of InputManager
 */
public class HumanBeingReader implements InputManager {
    private static Scanner scanner;

    public HumanBeingReader(Scanner sc) {
        scanner = sc;
        scanner.useDelimiter("\n");
    }

    public Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner sc) {
        scanner = sc;
    }

    public String readName() throws EmptyStringException {
        String s = scanner.nextLine().trim();
        if (s.equals("")) {
            throw new EmptyStringException();
        }
        return s;
    }

    public float readXCoord() throws InvalidNumberException {
        float x;
        try {
            x = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
        if (Float.isInfinite(x) || Float.isNaN(x)) throw new InvalidNumberException("invalid float value");
        return x;
    }

    public Long readYCoord() throws InvalidNumberException {
        long y;
        try {
            y = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
        if (y <= -684) throw new InvalidNumberException("must be greater than -684");
        return y;
    }

    public Coordinates readCoords() throws InvalidNumberException {
        float x = readXCoord();
        Long y = readYCoord();
        return new Coordinates(x, y);
    }

    public static Boolean parseBool() throws InvalidBooleanException {
        String buf = scanner.nextLine();
        return switch (buf.toLowerCase()) {
            case "true" -> true;
            case "false" -> false;
            case "" -> null;
            default -> throw new InvalidBooleanException("boolean must be true, false or null");
        };
    }

    public Boolean readRealHero() throws InvalidBooleanException {
        Boolean bool;
        bool = parseBool();
        if (bool == null) {
            throw new InvalidBooleanException("realHero must not be null");
        }
        return bool;
    }

    public Boolean readHasToothpick() throws InvalidBooleanException {
        Boolean bool;
        bool = parseBool();
        if (bool == null) {
            throw new InvalidBooleanException("hasToothpick must not be null");
        }
        return bool;
    }

    public float readImpactSpeed() throws InvalidNumberException {
        float impactSpeed;
        try {
            impactSpeed = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
        if (Float.isInfinite(impactSpeed) || Float.isNaN(impactSpeed))
            throw new InvalidNumberException("invalid float value");
        if (impactSpeed > 64.0f) throw new InvalidNumberException("impact speed must not be greater than 64");
        return impactSpeed;
    }

    public Long readMinutesOfWaiting() throws InvalidNumberException {
        long minutesOfWaiting;
        try {
            minutesOfWaiting = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
        return minutesOfWaiting;
    }

    public WeaponType readWeaponType() throws InvalidEnumException {
        String buf = scanner.nextLine().trim().toUpperCase();
        if (buf.equals("")) {
            return null;
        } else {
            try {
                return WeaponType.valueOf(buf);
            } catch (IllegalArgumentException e) {
                throw new InvalidEnumException();
            }
        }
    }

    public Mood readMood() throws InvalidEnumException {
        String buf = scanner.nextLine().trim().toUpperCase();
        if (buf.equals("")) {
            return null;
        } else {
            try {
                return Mood.valueOf(buf);
            } catch (IllegalArgumentException e) {
                throw new InvalidEnumException();
            }
        }
    }

    public Boolean readCoolness() throws InvalidBooleanException {
        Boolean isCool;
        isCool = parseBool();
        if (isCool == null) {
            throw new InvalidBooleanException("Coolness must not be null");
        }
        return isCool;
    }

    public Car readCar() throws InvalidDataException {
        Boolean isCool = readCoolness();
        return new Car(isCool);
    }

    public HumanBeing readHumanBeing() throws InvalidDataException {
        HumanBeing person;

        String name = readName();
        Coordinates coords = readCoords();
        Boolean realHero = readRealHero();
        Boolean hasToothpick = readHasToothpick();
        float impactSpeed = readImpactSpeed();
        long minutesOfWaiting = readMinutesOfWaiting();
        WeaponType weaponType = readWeaponType();
        Mood mood = readMood();
        Car car = readCar();
        person = new HumanBeing(name, coords, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);

        return person;

    }

    @Override
    public CommandWrapper readCommand() {
        return null;
    }
}