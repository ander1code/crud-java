package crudjava.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {

    private static Connection conn = null;
    
    public static Connection Connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite3");
            conn.setAutoCommit(false);
            return conn;
        } catch (ClassNotFoundException | SQLException e){
            return null;
        }
    }
}
