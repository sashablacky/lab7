package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

import java.util.LinkedList;

public class MinByMinutesOfWaiting implements Command {

    private static long minutesOfWaiting;
    HumanBeingCollectionManager collectionManager;


    public MinByMinutesOfWaiting(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public void setMin(long minMinutes){
        this.minutesOfWaiting = minMinutes;
    }
    public static long getMin(){
        return minutesOfWaiting;
    }
    @Override
    public HTTPResponse execute(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();
        long minutesOfWaiting = Long.MAX_VALUE;
        LinkedList<HumanBeing> Collection = collectionManager.getCollection();
        for(HumanBeing humanBeing : Collection){
            long newMinutesOfWaiting = humanBeing.getMinutesOfWaiting();
            if (minutesOfWaiting > newMinutesOfWaiting){
                minutesOfWaiting = newMinutesOfWaiting;
            }
        }
        if(minutesOfWaiting != Long.MAX_VALUE){
            httpResponse.pack(new String[]{String.valueOf(minutesOfWaiting)}, 200, "OK");
            setMin(minutesOfWaiting);
            System.out.println("MinByMinutesOfWaiting Выполнено успешно");
        } else {
            httpResponse.pack(new String[]{String.valueOf(minutesOfWaiting)}, 500, "Element was not found");
            System.out.println("Что-то пошло не так");
        }
            return httpResponse;
    }
}
