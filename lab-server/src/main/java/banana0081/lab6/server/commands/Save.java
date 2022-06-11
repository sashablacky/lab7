package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CollectionManager;
import banana0081.lab6.exceptions.FileException;
import banana0081.lab6.server.interfaces.Command;
import static banana0081.lab6.io.OutputManager.printErr;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Save implements Command {

    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager){this.collectionManager = collectionManager;}


    @Override
    public Pack execute(Pack pack) {
        try{
            String path = System.getenv("FILE_PATH");
            if (path == null) throw new IOException();

            File file = new File(path);

            if(!file.exists()) {
                System.out.println("file " + path +" does not exist, trying to create it");
                file.createNewFile();
            }
            if(!file.canWrite()) throw new IOException("Cannot write file");
            FileOutputStream fos = new FileOutputStream(file);
            XmlMapper mapper = new XmlMapper();
            mapper.writeValue(fos, collectionManager.getCollection());
            fos.close();
        } catch(FileException e){
            printErr(e.getMessage());
        } catch (IOException e) {
            printErr("cannot access file");
        }
        pack.pack("коллекция сохранена в файл\n");
        return pack;
    }
}
