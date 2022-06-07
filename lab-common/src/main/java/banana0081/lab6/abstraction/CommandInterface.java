package banana0081.lab6.abstraction;

import banana0081.lab6.Pack;

public interface CommandInterface {
    public Pack execute(String nameCommand, Pack pack);
}
