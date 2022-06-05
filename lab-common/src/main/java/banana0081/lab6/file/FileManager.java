package banana0081.lab6.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;

import static banana0081.lab6.io.OutputManager.print;
import static banana0081.lab6.io.OutputManager.printErr;

public class FileManager implements FileInterface{
    private String path;

    public FileManager(String pth){
        path = pth;
    }

    public void setPath(String pth){
        path = pth;
    }

    public LocalDateTime CreationDate = LocalDateTime.now();

    public LocalDateTime getCreationDate(){
        return CreationDate;
    }

    public void setCreationDate(LocalDateTime NewCreationDate){this.CreationDate = NewCreationDate;}

    public FileManager(){
        path = null;
    }
    public String read(String ScriptPath){
        String res = "";
        try {
            File file = new File(ScriptPath);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = bis.readAllBytes();
            res = new String(bytes, StandardCharsets.UTF_8);
            bis.close();
        }
        catch (IOException e){
            printErr(e.getMessage());
        }
        return res;
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

    private void create(File file) throws CreatingFileException{
        try{
            file.createNewFile(); 
        } catch(IOException e){
            throw new CreatingFileException();
        }
    }
    public boolean write(Collection<HumanBeing> collection){
        boolean res = true;
        try{
            if (path == null) throw new NoPathException();

            File file = new File(path);

            if(!file.exists()) {
                print("file " + path +" does not exist, trying to create it");
                create(file);
            }
            if(!file.canWrite()) throw new FileWrongPermissionsException("Cannot write file");
            FileOutputStream fos = new FileOutputStream(file);
            XmlMapper mapper = new XmlMapper();
            mapper.writeValue(fos, collection);
            fos.close();
        } catch(FileException e){
            printErr(e.getMessage());
            res = false;
        } catch (IOException e) {

            res = false;
            printErr("cannot access file");
        }
        return res;
    }
}
