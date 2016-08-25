package javacadapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static Connection Conectar() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
            conn.setAutoCommit(false);
            return conn;
        } catch (Exception e) {
            return null;
        }
    }
}
