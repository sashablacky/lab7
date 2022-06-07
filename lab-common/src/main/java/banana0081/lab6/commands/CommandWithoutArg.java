package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterface;

public class CommandWithoutArg implements CommandInterface {

    @Override
    public Pack execute(String nameCommand, Pack pack) {
        pack.pack(nameCommand);
        return pack;
    }
}
