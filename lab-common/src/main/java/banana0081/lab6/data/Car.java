package banana0081.lab6.data;

public class Car implements Validatable {
    private Boolean cool;

    public Car(Boolean howCool){
        cool = howCool;
    }
    public Car(){}
    /** 
     * @return Boolean
     */
    public Boolean isCool(){
        return cool;
    }

    public void setCool(Boolean cool){ this.cool = cool;
    }

    /** 
     * @return String
     */
    @Override
    public String toString(){
        String s = "";
        s += "{";
        s += "  \"cool\" : " + cool + "\n";
        s += "}";
        return s;
    }

    public boolean validate(){
        return (cool!=null);
    }
}
