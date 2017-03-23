/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Simon
 */
public class ConnectionManager {
    
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\Simon\\Desktop\\bazy\\src\\bazy\\nowa";

    protected static Connection con;
    protected static Statement stat;
    protected static PreparedStatement pst;
    protected static ResultSet rs;
        
     public static Connection getConnection(){ 
    
        
     try {
            Class.forName(DRIVER);
            System.out.println("zar ster");
        } catch (ClassNotFoundException e) {
            System.err.println("zly sterownik");
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DB_URL);
            System.out.println("nawiązano");
        } catch (SQLException e) {
            System.err.println("nie dziala");
            e.printStackTrace();
        }
        try {

            stat = con.createStatement();

            String sql = "SELECT * from PRACOWNICY";
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                int ID = rs.getInt("ID");
                String imie = rs.getString("IMIE");
                System.out.println(ID + "  " + imie);
                
            }rs.close();
        } catch (SQLException e) {
            System.err.println("nie działa");
        }
        return con;
}
}

