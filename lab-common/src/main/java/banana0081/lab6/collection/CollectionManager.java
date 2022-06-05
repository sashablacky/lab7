package banana0081.lab6.collection;

import banana0081.lab6.data.HumanBeing;

import java.util.LinkedList;

/**
 * interface for storing elements
 * @param <T> type of elements
 */
public interface CollectionManager<T> {
    /**
     * generates new unique ID for collection
     * @return
     */
    public int generateNextId();

    public void sort();
    
    public LinkedList<T> getCollection();

    /**
     * adds new element
     * @param element
     */
    public void add(T element);

    /**
     * get information about collection
     * @return info
     */
    public String getInfo();

    /**
     * checks if collection contains element with particular id
     * @param ID
     * @return
     */
    public boolean checkID(Integer ID);

    /**
     * removes element by id
     * @param id
     */
    public void removeByID(Integer id);

    /**
     * updates element by id
     * @param id
     * @param newElement
     */
    public void updateByID(Integer id, T newElement);

    /**
     * get collection size
     * @return
     */
    public int getSize();
   
    public void clear();

    public void load(LinkedList<HumanBeing> data);

    public void removeFirst();

    public void removeLast();

    public void shuffle();

    public void sum_of_minutes_of_waiting();

    public void min_by_minutes_of_waiting();

    public void print_unique_impact_speed();

}
