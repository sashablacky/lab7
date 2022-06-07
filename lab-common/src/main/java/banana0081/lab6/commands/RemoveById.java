package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterfaceWithArgument;

public class RemoveById implements CommandInterfaceWithArgument {
    private String[] arg;
    @Override
    public Pack execute(String nameCommand, Pack pack) {
        pack.pack(nameCommand, arg);
        return pack;
    }

    @Override
    public void getArg(String[] arg) {
        this.arg = arg;
    }
}
