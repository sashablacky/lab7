package banana0081.lab6.data;
/**
 * interface for objects that can be stored in collections
 */
public interface Collectionable extends Comparable<Collectionable>, Validatable {
    int getId();
    /**
     * sets id, useful for replacing object in collection
     * @param ID
     */
    void setId(int ID);
    
    float getImpactSpeed();

    String getName();

    /**
     * compairs two objects
     */
    int compareTo(Collectionable worker);

    boolean validate();
}
