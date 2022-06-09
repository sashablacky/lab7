package banana0081.lab6.server;

import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.*;
import banana0081.lab6.exceptions.FileDoesNotExistException;
import banana0081.lab6.exceptions.FileWrongPermissionsException;
import banana0081.lab6.exceptions.NoPathException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Scanner;

import static banana0081.lab6.io.OutputManager.printErr;

public class FileWorker {
    String path = System.getenv("FILE_PATH");
    private HumanBeingCollectionManager collectionManager;

    public FileWorker(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public LinkedList<HumanBeing> load(){
        LinkedList<HumanBeing> value = new LinkedList<HumanBeing>();
        try{
            if (path == null) throw new NoPathException();
            File file = new File(path);
            if (!file.exists()) throw new FileDoesNotExistException();
            if(!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
            XmlMapper xmlMapper = new XmlMapper();
            String xmlString = readXmlString(file);
            value = xmlMapper.readValue(xmlString, new TypeReference<LinkedList<HumanBeing>>() {});
        } catch(IOException e){
            printErr(e.getMessage());
        }
        return value;
    }
    private String readXmlString(File file) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = bis.readAllBytes();
        String res = new String(bytes, StandardCharsets.UTF_8);
        bis.close();
        return res;
    }
        }
