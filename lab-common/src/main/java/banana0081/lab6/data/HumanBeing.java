package banana0081.lab6.data;

import banana0081.lab6.collection.HumanBeingCollectionManager;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Human class
 */
public class HumanBeing implements Collectionable, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Boolean realHero; //Поле не может быть null
    private Boolean hasToothpick; //Поле не может быть null
    private float impactSpeed; //Максимальное значение поля: 64
    private long minutesOfWaiting;
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле не может быть null


    /**
     * Constructor, set fields
     * @param name
     * @param coordinates
     * @param realHero
     * @param hasToothpick
     * @param impactSpeed
     * @param minutesOfWaiting
     * @param weaponType
     * @param mood
     * @param car
     */
    public HumanBeing(String name, Coordinates coordinates, Boolean realHero, Boolean hasToothpick, float impactSpeed, long minutesOfWaiting, WeaponType weaponType, Mood mood, Car car){
        creationDate = new Date();
        
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }
    public HumanBeing(int id, String s, Coordinates coordinates, Boolean aBoolean, Boolean readHasToothpick, float v, Long aLong, WeaponType weaponType, Mood mood, Boolean readCoolness, Car car){}
    public HumanBeing(){};



    /** 
     * @return int
     */
    public int getId(){
        return id;
    }

    /** change ID for replacing objects in collection 
     * @param NewId
     */
    public void setId(int NewId){
        this.id = NewId;
    }

    /** 
     * @return String
     */
    public String getName(){
        return name;
    }


    public void setName(String NewName){
        this.name = NewName;
    }

    /**
     * @return Coordinates
     */
    public Coordinates getCoordinates() {return coordinates; }

    public void setCoordinates(Coordinates NewCoordinates) {this.coordinates = NewCoordinates; }

    /**
     * @return CreationDate
     */

    public Date getCreationDate() {return creationDate; }

    public void setCreationDate(Date NewCreationDate) {this.creationDate = NewCreationDate; }
    /** 
     * @return Boolean
     */
    public Boolean getRealHero(){
        return realHero;
    }

    public void setRealHero(Boolean NewRealHero){
        this.realHero = NewRealHero;
    }
    /** 
     * @return Boolean
     */
    public Boolean getHasToothpick(){
        return realHero;
    }

    public void setHasToothpick(Boolean hasToothpick){
        this.hasToothpick = hasToothpick;
    }

    /** 
     * @return float
     */
    public float getImpactSpeed(){
        return impactSpeed;
    }

    public void setImpactSpeed(float NewImpactSpeed){
        this.impactSpeed = NewImpactSpeed;
    }

    /** 
     * @return long
     */
    public long getMinutesOfWaiting(){
        return minutesOfWaiting;
    }

    public void setMinutesOfWaiting(long NewMinutesOfWaiting){
        this.minutesOfWaiting = NewMinutesOfWaiting;
    }

    /**
     * @return WeaponType
     */
    public WeaponType getWeaponType(){
        return weaponType;
    }

    public void setWeaponType(WeaponType NewWeaponType){
        this.weaponType = NewWeaponType;
    }

    /**
     * @return Mood
     */
    public Mood getMood(){
        return mood;
    }

    public void setMood(Mood NewMood) {this.mood = NewMood;}

    /**
     * @return Car
     */
    public Car getCar(){
        return car;
    }

    public void setCar(Car NewCar) {this.car = NewCar;}
    
    /**
     * @return String
     */
    @Override
    public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strCreationDate = dateFormat.format(creationDate);
        String s = "";
        s += "{\n";
        s += "  \"id\" : " + id + ",\n";
        s += "  \"name\" : " + "\"" + name + "\"" + ",\n";
        s += "  \"coordinates\" : " + coordinates.toString() + ",\n";
        s += "  \"creationDate\" : " + "\"" + strCreationDate + "\"" + ",\n";
        s += "  \"realHero\" : " + realHero + ",\n";
        s += "  \"hasToothpick\" : " + hasToothpick + ",\n";
        s += "  \"impactSpeed\" : " + impactSpeed + ",\n";
        s += "  \"minutesOfWaiting\" : " + minutesOfWaiting + ",\n";
        if (mood!=null) s += "  \"weaponType\" : " + weaponType.toString() + ",\n";
        if (mood!=null) s += "  \"mood\" : " + mood + ",\n";
        s += "  \"car\" : " + car.toString() + "\n";
        s += "}";
        return s;
    }
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        HumanBeing another = (HumanBeing)obj;
        return this.getId() == another.getId();
    }

    /** 
     * @param HumanBeing
     * @return int
     */
    public int compareTo(Collectionable HumanBeing){
        return Float.compare(this.impactSpeed, HumanBeing.getImpactSpeed());
    }

    /** 
     * @return boolean
     */
    public boolean validate(){
        return (
            (id>0) && name!=null && !name.equals("") &&
            coordinates != null && coordinates.validate() && creationDate!=null && 
            realHero != null && hasToothpick != null && (impactSpeed<=64) &&
            car != null && car.validate()
        );

    }
}
