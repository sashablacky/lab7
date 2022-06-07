package banana0081.lab6.server.interfaces;

import banana0081.lab6.Pack;

public interface CommandInterfaceServer {
    Pack execute(Pack pack);
    String getDescription();
}
