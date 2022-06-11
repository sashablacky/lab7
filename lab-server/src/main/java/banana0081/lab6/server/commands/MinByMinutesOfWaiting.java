package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
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

    public Pack execute(Pack pack) {
        long minutesOfWaiting = Long.MAX_VALUE;
        LinkedList<HumanBeing> Collection = collectionManager.getCollection();
        for(HumanBeing humanBeing : Collection){
            long newMinutesOfWaiting = humanBeing.getMinutesOfWaiting();
            if (minutesOfWaiting > newMinutesOfWaiting){
                minutesOfWaiting = newMinutesOfWaiting;
            }
        }
        if(minutesOfWaiting != Long.MAX_VALUE){
            pack.pack(String.valueOf(minutesOfWaiting));
            setMin(minutesOfWaiting);
            System.out.println("MinByMinutesOfWaiting Выполнено успешно");
        } else {
            System.out.println("Что-то пошло не так");
        }
        return pack;
    }
}
