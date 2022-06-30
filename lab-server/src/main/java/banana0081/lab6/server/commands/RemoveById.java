package banana0081.lab6.server.commands;

import banana0081.lab6.Pack;
import banana0081.lab6.collection.HumanBeingCollectionManager;
import banana0081.lab6.http.HTTPRequest;
import banana0081.lab6.http.HTTPResponse;
import banana0081.lab6.server.interfaces.CommandWithArguments;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RemoveById implements CommandWithArguments {
    private HumanBeingCollectionManager collectionManager;
    private Scanner in;
    private String[] arguments;

    RemoveById (HumanBeingCollectionManager collectionManager, Scanner in) {
        this.collectionManager = collectionManager;
        this.in = in;
    }

    @Override
    public HTTPResponse execute(HTTPRequest httpRequest, Connection conn) {
        HTTPResponse httpResponse = new HTTPResponse();
        try{
            String sql = "DELETE * FROM HUMANBEING WHERE ID = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(httpRequest.getArguments()[0]));
            ps.executeQuery().close();
            ps.close();
            for (String argument : httpRequest.getArguments()) {
                collectionManager.removeByID(Integer.parseInt(argument));
            }
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        httpResponse.pack(200, "OK");
        return httpResponse;
    }
    @Override
    public void getArgs(String[] arguments) {
        this.arguments = arguments;
    }
}
