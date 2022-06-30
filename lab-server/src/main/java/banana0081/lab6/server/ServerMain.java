package banana0081.lab6.server;

import banana0081.lab6.server.connection.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerMain {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "1983");
            System.out.println("Opened database successfully");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Server server = new Server(conn);
        server.run(8080);
    }
}
