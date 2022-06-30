package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Clear implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Clear(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest, Connection conn) {
        try{
            String sql = "DELETE FROM HUMANBEING;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();
            collectionManager.clear();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        HTTPResponse httpResponse = new HTTPResponse();
        if (collectionManager.getCollection().isEmpty()){
            httpResponse.pack(200, "OK");
        } else{
            httpResponse.pack(500,"Internal Error");
        }
        return httpResponse;
    }
}
