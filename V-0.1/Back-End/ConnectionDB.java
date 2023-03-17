import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection createConnection(String url, String name, String password) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, name, password);
            if (conn == null) {
                System.out.println("Errore connessione");
            } else {
                System.out.println("Connesso");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return conn;
    }
}