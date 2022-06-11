package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.server.interfaces.Command;

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
    public Pack execute(Pack pack) {
        long res = 0;
        LinkedList<HumanBeing> Collection = collectionManager.getCollection();
        for (HumanBeing humanBeing : Collection){
            res += humanBeing.getMinutesOfWaiting();
        }
        if(res != 0){
            setResult(res);
            pack.pack(String.valueOf(res));
            System.out.println("SumOfMinutesOfWaiting Выполнено успешно");
        } else {
            System.out.println("Что-то пошло не так");
        }
        return pack;
    }
}
