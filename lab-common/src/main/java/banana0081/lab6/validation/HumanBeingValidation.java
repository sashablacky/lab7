package banana0081.lab6.validation;


import banana0081.lab6.data.Car;
import banana0081.lab6.data.Mood;
import banana0081.lab6.data.WeaponType;

import static banana0081.lab6.io.OutputManager.printErr;

public class HumanBeingValidation {
    public boolean nameHumanBeingValid(String nameDragon) {
        if (nameDragon.equals("")) {
            printErr("Имя не может быть пустой строкой!");
            return false;
        }
        return true;
    }

    public boolean CoordinateXYValid(Float x,int y) {
        if (x == null & y >= -684 ) {
            printErr("Координаты введены не правильно! координата x не может быть null или координата не должна превышать -684");
            return false;
        }
        return true;
    }

    public boolean RealHeroValid(Boolean realHero) {
        if (realHero == null) {
            printErr("RealHero не может быть null!");
            return false;
        }
        return true;
    }

    public boolean hasToothpickValid(Boolean hasToothPick) {
        if (hasToothPick == null) {
            printErr("hasToothpickValid не может быть null!");
            return false;
        }
        return true;
    }

    public boolean impactSpeedValid(float impactSpeed) {
        if (impactSpeed >= 65) {
            printErr("impactSpeed не может быть больше 64!");
            return false;
        }
        return true;
    }

    public boolean weaponTypeValid(WeaponType weaponType) {
        if (weaponType == null) {
            printErr("weaponType не может быть null!");
            return false;
        }
        return true;
    }

    public boolean moodValid(Mood mood) {
        if (mood == null) {
            printErr("mood не может быть null!");
            return false;
        }
        return true;
    }

    public boolean CarValid(Car car) {
        if (car == null) {
            printErr("car не может быть null!");
            return false;
        }
        return true;
    }
}
