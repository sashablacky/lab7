package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

import java.sql.Connection;
import java.util.LinkedList;

public class SumOfMinutesOfWaiting implements Command {

    HumanBeingCollectionManager collectionManager;
    private static long Result;
    public void setResult(long result){
        this.Result = result;
    }

    public long getResult(){
        return Result;
    }
    public SumOfMinutesOfWaiting(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public HTTPResponse execute(HTTPRequest httpRequest, Connection conn) {
        long res = 0;
        HTTPResponse httpResponse = new HTTPResponse();
        LinkedList<HumanBeing> Collection = collectionManager.getCollection();
        for (HumanBeing humanBeing : Collection){
            res += humanBeing.getMinutesOfWaiting();
        }
        if(res != 0){
            setResult(res);
            httpResponse.pack(new String[]{String.valueOf(res)}, 200, "OK");
            System.out.println("SumOfMinutesOfWaiting Выполнено успешно");
        } else {
            httpResponse.pack(500, "Internal Error");
            System.out.println("Что-то пошло не так");
        }
        return httpResponse;
    }
}
