package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.abstraction.CollectionManager;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.exceptions.FileException;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;
import static banana0081.lab6.io.OutputManager.printErr;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Save{

    private final HumanBeingCollectionManager collectionManager;
    private Connection conn;
    public Save(HumanBeingCollectionManager collectionManager, Connection conn){this.collectionManager = collectionManager; this.conn = conn;}

    public HTTPResponse execute(HTTPRequest httpRequest) {
        HTTPResponse httpResponse = new HTTPResponse();
        try{
            String sql = "DELETE FROM HUMANBEING;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        try{
            for(HumanBeing humanBeing : collectionManager.getCollection()){
                String sql = "INSERT INTO HUMANBEING (NAME, X_COORD, Y_COORD, CREATION_DATE, REAL_HERO, TOOTHPICK, IMPACT_SPEED, MINUTES, CREATOR, WEAPON_TYPE, MOOD, COOLNESS) VALUES(" + humanBeing.toSqlString() + ");";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        httpResponse.pack(200, "OK");
        return httpResponse;
    }
}
