package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.server.interfaces.CommandWithArguments;

public class ExecuteScript implements CommandWithArguments {

    String[] arguments;

    @Override
    public Pack execute(Pack pack) {
        pack.pack("Скрипт выполнен\n");
        return pack;
    }

    @Override
    public void getArgs(String[] arguments) {
        this.arguments = arguments;
    }
}
