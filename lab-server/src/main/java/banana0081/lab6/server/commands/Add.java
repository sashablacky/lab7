package banana0081.lab6.server.commands;
import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.data.HumanBeing;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add implements Command {
    private final HumanBeingCollectionManager collectionManager;

    public Add(HumanBeingCollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest, Connection conn) {
        int previousSize = collectionManager.getPreviousSize();
        HTTPResponse httpResponse = new HTTPResponse();
        String creator = httpRequest.getAuthorization();
        HumanBeing humanBeing = httpRequest.getHumanBeing();
        humanBeing.setCreator(creator);
        try{
                String sql = "INSERT INTO HUMANBEING (NAME, X_COORD, Y_COORD, CREATION_DATE, REAL_HERO, TOOTHPICK, IMPACT_SPEED, MINUTES, CREATOR, WEAPON_TYPE, MOOD, COOLNESS) VALUES(" + humanBeing.toSqlString() + ");";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.execute();
                ps.close();
                collectionManager.add(humanBeing);
            } catch(SQLException throwables) {
            throwables.printStackTrace();
        }

        if (previousSize < collectionManager.getSize()){
            httpResponse.pack(201, "Created");
        } else {
            httpResponse.pack(500, "Internal Error");
        }
        return httpResponse;
    }

}
