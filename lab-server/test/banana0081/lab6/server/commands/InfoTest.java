package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoTest {

    HumanBeingCollectionManager collectionManager = new HumanBeingCollectionManager();
    private Info info;


    @Test
    public void isOutputed(){
        String expected = collectionManager.getInfo();
        Info info = new Info(collectionManager);
        info.execute(new HTTPRequest());
        assertEquals(expected, info.getInfo());
    }

}