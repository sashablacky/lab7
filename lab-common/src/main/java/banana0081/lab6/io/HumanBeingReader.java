package banana0081.lab6.io;

import banana0081.lab6.commands.CommandWrapper;
import banana0081.lab6.data.*;
import banana0081.lab6.exceptions.*;

import java.util.Scanner;

/**
 * basic implementation of InputManager
 */
public class HumanBeingReader implements InputManager {
    private static Scanner scanner = new Scanner(System.in);
    private static Boolean consoleMode = true;
    public HumanBeingReader(Scanner sc) {
        this.scanner = sc;
        scanner.useDelimiter("\n");
    }
    public HumanBeingReader() {
    }
    public Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner sc) {
        scanner = sc;
    }

    public static void setConsoleMode(Boolean cMode) {
        consoleMode = cMode;
    }

    public String readName(){
        if(consoleMode) {
            System.out.println("Введите имя: ");
        }
        String s = scanner.nextLine().trim();
        if (s.equals("")) {
            System.out.println("Имя не должно быть пустой строкой");
            return readName();
        }
        return s;
    }

    public float readXCoord() throws InvalidNumberException {
        float x;
        if(consoleMode) {
            System.out.println("Введите x (дробное число): ");
        }
        try {
            x = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
        if (Float.isInfinite(x) || Float.isNaN(x)) throw new InvalidNumberException("invalid x float value");
        return x;
    }

    public Long readYCoord() throws InvalidNumberException {
        long y;
        if(consoleMode) {
            System.out.println("Введите y (целое число большее -684): ");
        }
        try {
            y = Long.parseLong(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new InvalidNumberException();
        }
        if (y <= -684) throw new InvalidNumberException("y must be greater than -684");
        return y;
    }

    public Coordinates readCoords(){
        try {
            float x = readXCoord();
            Long y = readYCoord();
            return new Coordinates(x, y);
        } catch (InvalidNumberException e){System.out.println(e.getMessage());
        return readCoords();}
    }

    public static Boolean parseBool() throws InvalidBooleanException{
        String buf = scanner.nextLine();
        return switch (buf.toLowerCase()) {
            case "true" -> true;
            case "false" -> false;
            case "" -> null;
            default -> throw new InvalidBooleanException("boolean must be true, false or null");
        };
    }

    public Boolean readRealHero(){
        if(consoleMode) {
            System.out.println("Введите realHero (true/false): ");
        }
        Boolean bool;
        try {
            bool = parseBool();
            if (bool == null) {
                throw new InvalidBooleanException("realHero must not be null");
            }
            return bool;
        } catch (InvalidBooleanException e){
            System.out.println(e.getMessage());
            return readRealHero();
        }
    }

    public Boolean readHasToothpick(){
        if(consoleMode) {
            System.out.println("Введите toothpick (true/false): ");
        }
        Boolean bool;
        try {
            bool = parseBool();
            if (bool == null) {throw new InvalidBooleanException("hasToothpick must not be null");}
            return bool;
        }
        catch (InvalidBooleanException e){
                System.out.println(e.getMessage());
                return readHasToothpick();
            }
        }

    public float readImpactSpeed(){
        if(consoleMode) {
            System.out.println("Введите impactSpeed (дробное число, меньшее 64): ");
        }
        try {
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
        } catch (InvalidNumberException e){System.out.println(e.getMessage());
        return readImpactSpeed();}

    }

    public Long readMinutesOfWaiting(){
        if(consoleMode) {
            System.out.println("Введите minutesOfWaiting (целое число): ");
        }
        try {
            long minutesOfWaiting;
            try {
                minutesOfWaiting = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new InvalidNumberException();
            }
            return minutesOfWaiting;
        } catch(InvalidNumberException e){System.out.println(e.getMessage());
        return readMinutesOfWaiting();}

    }

    public WeaponType readWeaponType() {
        if(consoleMode) {
            System.out.println("Введите weaponType (AXE, SHOTGUN, MACHINE_GUN) или оставьте поле пустым: ");
        }
        String buf = scanner.nextLine().trim().toUpperCase();
        if (buf.equals("")) {
            return null;
        } else {
            try {
                return WeaponType.valueOf(buf);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return readWeaponType();
            }
        }
    }

    public Mood readMood(){
        if(consoleMode) {
            System.out.println("Введите mood (SORROW, LONGING, GLOOM, CALM) или оставьте поле пустым: ");
        }
        String buf = scanner.nextLine().trim().toUpperCase();
        if (buf.equals("")) {
            return null;
        } else {
            try {
                return Mood.valueOf(buf);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return readMood();
            }
        }
    }

    public Boolean readCoolness(){
        if(consoleMode) {
            System.out.println("Введите крутость машины (true/false): ");
        }
        Boolean isCool;
        try {
            isCool = parseBool();
            if (isCool == null) {
                throw new InvalidBooleanException("Coolness must not be null");
            }
            return isCool;
        } catch (InvalidBooleanException e){System.out.println(e.getMessage());
        return readCoolness();}

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