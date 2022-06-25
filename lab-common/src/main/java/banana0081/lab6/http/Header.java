package banana0081.lab6.http;

import java.io.Serializable;

public class Header implements Serializable {
    String name;
    String value;
    public Header(){};
    public Header(String name){this.name = name; this.value = " ";};
    public Header(String name, String value){this.name = name; this.value = value;};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
