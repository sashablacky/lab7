package banana0081.lab6.client;

import banana0081.lab6.exceptions.FileDoesNotExistException;
import banana0081.lab6.exceptions.NoSuchCommandException;
import banana0081.lab6.file.FileManager;
import banana0081.lab6.io.InputManagerImpl;

import static banana0081.lab6.io.OutputManager.printErr;

public class Application {


    public Application() {
        FileManager collectionFileReader = new FileManager();
    }

    public void launchApplication() {
        try {
            InputManagerImpl.readCommand();
        } catch (NoSuchCommandException e){
            printErr("Внимание! ");
        }  catch (NullPointerException e) {
            printErr("Пожалуйста проинициализируйте системную переменную HUMAN_INFO, "
                    + "содержащую путь до файла с информацией о коллекции");
        }
    }
}