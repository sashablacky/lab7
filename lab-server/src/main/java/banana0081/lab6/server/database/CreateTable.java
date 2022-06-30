package banana0081.lab6.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class CreateTable {
    public static void main( String args[] ) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "1983");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            /*String sql = "CREATE TABLE HUMANBEING " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " CREATOR        TEXT     NOT NULL, " +
                    " X_COORD        INT      NOT NULL, " +
                    " Y_COORD         REAL    NOT NULL," +
                    " CREATION_DATE BIGINT    NOT NULL," +
                    " REAL_HERO BOOLEAN       NOT NULL," +
                    " TOOTHPICK BOOLEAN       NOT NULL," +
                    " IMPACT_SPEED REAL       NOT NULL," +
                    " WEAPON_TYPE     TEXT," +
                    " MOOD           TEXT," +
                    " COOLNESS       BOOLEAN  NOT NULL)"
                    ;*/
            String sql = "CREATE TABLE USERS " +
                    "(ID SERIAL PRIMARY KEY     NOT NULL," +
                    " LOGIN        TEXT    NOT NULL," +
                    " PASSWORD     TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}