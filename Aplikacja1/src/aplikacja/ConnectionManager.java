package aplikacja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\simon\\Desktop\\Aplikacja1\\src\\aplikacja\\przychodnia.db";

    protected static Connection con;
    protected static Statement stat;
    protected static PreparedStatement pst;
    protected static ResultSet rs;
    private static Wyjatki wyjatki = new Wyjatki();

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);
            System.out.println("zar ster");
        } catch (ClassNotFoundException e) {
            System.err.println("zly sterownik");
            e.printStackTrace(); }
        try {
            con = DriverManager.getConnection(DB_URL);
            System.out.println("nawiązano");
        } catch (SQLException e) {
            System.err.println("nie dziala");
            e.printStackTrace();
        }
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            wyjatki.badConnection();
        }
        return con;
    }
}
