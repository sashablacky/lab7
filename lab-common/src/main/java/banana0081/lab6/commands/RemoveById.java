package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterfaceWithArgument;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HttpMethod;

public class RemoveById implements CommandInterfaceWithArgument {
    private String[] arg;
    @Override
    public HTTPRequest execute(String nameCommand, HTTPRequest pack) {
        pack.pack(nameCommand, arg, HttpMethod.DELETE);
        return pack;
    }

    @Override
    public void getArg(String[] arg) {
        this.arg = arg;
    }
}
