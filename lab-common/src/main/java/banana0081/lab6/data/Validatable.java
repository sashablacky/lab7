package banana0081.lab6.data;

public interface Validatable {
    /**
     * validates all fields after json deserialization
     * @return
     */
    boolean validate();
}