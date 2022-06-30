package banana0081.lab6.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CommandInterface;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HttpMethod;

import java.lang.reflect.Method;

public class CommandWithoutArg implements CommandInterface {

    @Override
    public HTTPRequest execute(String nameCommand, HTTPRequest httpRequest) {
        if(nameCommand.equals("help") || nameCommand.equals("show") || nameCommand.equals("info")) {
            httpRequest.pack(nameCommand, HttpMethod.GET);
        }
        else{
            httpRequest.pack(nameCommand, HttpMethod.POST);
        }
        return httpRequest;
    }
}
