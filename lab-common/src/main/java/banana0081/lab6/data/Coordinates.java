package banana0081.lab6.data;

import java.io.Serializable;

public class Coordinates implements Validatable, Serializable {
    private float x;
    private Long y; //Значение поля должно быть больше -123, Поле не может быть null
    public Coordinates(float x, Long y){
        this.x = x;
        this.y = y;
    }
    public Coordinates(){}
    /**
     * @return x coord
     */
    public float getX() {
        return x;
    }

    /**
     * @return y coord
     */
    public Long getY() {
        return y;
    }

    @Override
    public String toString(){
        String s = "";
        s += "{\"x\" : " + x + ", ";
        s += "\"y\" : " + y + "}";
        return s;
    }

    public boolean validate(){
        return !(y==null || y <=-123 || Float.isInfinite(x) || Float.isNaN(x));
    }

}