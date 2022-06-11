package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.server.interfaces.Command;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class PrintUniqueImpactSpeed implements Command {
    HumanBeingCollectionManager collectionManager;
    public static String response;

    public String getResponse(){
        return response;
    }

    public void setResponse(String response){
        this.response = response;
    }


    public PrintUniqueImpactSpeed(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public Pack execute(Pack pack) {
        Set<Float> UniqueImpactSpeed = new HashSet<Float>();
        LinkedList<HumanBeing> Collection = collectionManager.getCollection();
        for(HumanBeing humanBeing : Collection){
            Float ImpactSpeed = humanBeing.getImpactSpeed();
            UniqueImpactSpeed.add(ImpactSpeed);
        }
        String response = "Уникальные значения impactSpeed\n";
        for(Float a : UniqueImpactSpeed){
            response+= (String.valueOf(a)) + '\n';
        }
        if(!response.equals("")){
            setResponse(response);
            System.out.println(response);
            pack.pack(response);
            System.out.println("PrintUniqueImpactSpeed Выполнено успешно");
        } else {
            System.out.println("Что-то пошло не так");
        }
        return pack;
    }
}
