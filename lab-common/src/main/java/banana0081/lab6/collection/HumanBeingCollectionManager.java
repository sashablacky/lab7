package banana0081.lab6.collection;

import banana0081.lab6.data.HumanBeing;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import static banana0081.lab6.io.OutputManager.print;

//@Alias LinkedList<HumanBeing>  NameToNumbers;

/**
 * Operates collection.
 */
public class HumanBeingCollectionManager implements CollectionManager<HumanBeing> {
    private LinkedList<HumanBeing> collection;
    private HashSet<Integer> uniqueIds;
    private java.time.LocalDateTime initDate;

    public HumanBeingCollectionManager(String sysEnvironment){
        uniqueIds = new HashSet<>();
        collection = new LinkedList<>();
        initDate = java.time.LocalDateTime.now();
    }
    public void load(LinkedList<HumanBeing> data){
        collection = data;
    }
    public int generateNextId(){
        if (collection.isEmpty())
            return 1;
        else {
            Integer id = collection.getLast().getId() + 1;
            if(uniqueIds.contains(id)){
                while (uniqueIds.contains(id)) id+=1;
            }
            uniqueIds.add(id);
            return id;
        }
    }
    public void sort(){
        Collections.sort(collection);
    }

    /**
     * Return collection
     * @return Collection
     */
    public LinkedList<HumanBeing> getCollection()
    {
        return collection;
    }


    /**
     * Get information about collection
     * @return Information
     */
    public String getInfo(){
        return "LinkedList of humans, size: " + Integer.toString(collection.size()) + ", initialization date: " + initDate.toString();
    }

    /**
     * Add element to collection
     * @param humanBeing Element of collection
     */
    public void add(HumanBeing humanBeing){
        humanBeing.setId(generateNextId());
        collection.add(humanBeing);
        print("Added element:");
        print(humanBeing.toString());
    }

    /**
     * Give info about is this ID used
     * @param ID ID
     * @return is it used or not
     */
    public boolean checkID(Integer ID){
        for (HumanBeing humanBeing: collection)
        {
            if (humanBeing.getId() == ID)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Delete element by ID
     * @param id ID
     */
    public void removeByID(Integer id){
        for (HumanBeing humanBeing : collection){
            if (humanBeing.getId() == id){
                collection.remove(humanBeing);
                uniqueIds.remove(id);
                print("element #"+Integer.toString(id)+" successfully deleted");
                return;
            }
        }
    }

    /**
    * Delete element by ID
    * @param id ID
    */
    public void updateByID(Integer id, HumanBeing newHumanBeing){
        int idx = 0;
        for (HumanBeing humanBeing : collection){
            if (humanBeing.getId() == id){
                newHumanBeing.setId(id);
                collection.set(idx, newHumanBeing);
                print("element #"+Integer.toString(id)+" successfully updated");
                return;
            }
            idx += 1;
        }
    }

    /**
     * Get size of collection
     * @return Size of collection
     */
    public int getSize(){
        return collection.size();
    }

    public void clear(){
        collection.clear();
        uniqueIds.clear();
    }

    public void removeFirst(){
        int id = collection.getFirst().getId();
        collection.removeFirst();
        uniqueIds.remove(id);
        print("element #"+Integer.toString(id)+" successfully deleted");

    }
    public void removeLast(){
        int id = collection.getLast().getId();
        collection.removeLast();
        uniqueIds.remove(id);
        print("element #"+Integer.toString(id)+" successfully deleted");
    }

    public void shuffle() {
        Collections.shuffle(collection);
        int id = 1;
        for (HumanBeing humanBeing: collection){
            humanBeing.setId(id);
            id+=1;
        }
    }

    public void print_unique_impact_speed(){
        LinkedList<Float> speeds = new LinkedList<>();
        print("unique impact speeds:");
        for (HumanBeing humanBeing: collection){
            if (! speeds.contains(humanBeing.getImpactSpeed())){
                print(humanBeing.getImpactSpeed());
                speeds.add(humanBeing.getImpactSpeed());
            }
        }
    }
    public void sum_of_minutes_of_waiting(){
        long sum = 0;
        for (HumanBeing humanBeing: collection){
            sum += humanBeing.getMinutesOfWaiting();
        }
        print(sum);
    }
    public void min_by_minutes_of_waiting(){
        long min_minutes = collection.getFirst().getMinutesOfWaiting();
        HumanBeing res = collection.getFirst();
        for (HumanBeing humanBeing: collection) {
            if (humanBeing.getMinutesOfWaiting() < min_minutes) {
                min_minutes = humanBeing.getMinutesOfWaiting();
                res = humanBeing;
            }
        }
        print(res.toString());
    }
}
