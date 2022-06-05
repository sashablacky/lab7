package banana0081.lab6.file;

import banana0081.lab6.data.HumanBeing;

import java.util.Collection;
import java.util.LinkedList;

public interface FileInterface {
    /**
     * set path to file
     * @param path
     */
    void setPath(String path);

    /**
     * read data
     * @return
     */
    LinkedList load();
    String read(String ScriptPath);
    /**
     * write data
     * @param collection
     */
    boolean write(Collection<HumanBeing> collection);
}
