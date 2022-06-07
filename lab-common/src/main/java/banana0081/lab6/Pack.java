package banana0081.lab6;

import banana0081.lab6.data.HumanBeing;

import java.io.Serializable;

public class Pack implements Serializable {
    String commandName;
    String[] arg;
    HumanBeing humanBeing;

    public void pack(String commandName) {
        this.commandName = commandName;
    }

    public void pack(String commandName, String[] arg) {
        this.commandName = commandName;
        this.arg = arg;
    }

    public void pack(String commandName, HumanBeing dragon) {
        this.commandName = commandName;
        this.humanBeing = dragon;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArg() {
        return arg;
    }

    public HumanBeing getHumanBeing() {
        return humanBeing;
    }


}