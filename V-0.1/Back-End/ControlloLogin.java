import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ControlloLogin {

    public static boolean autenticazioneAccesso(String email, String password) {
        boolean ritorno = false;
        try {
            String query = "SELECT * From utenti WHERE Email LIKE " + "'" + email + "'";
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                if (password.equals(rs.getString(2))) {
                    ritorno = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore SQL " + e.getMessage());
        }
        return ritorno;

    }

    public static ArrayList<String> stampaSavedAppuntamenti(String email) {
        ArrayList<String> risultato = new ArrayList<String>();
        try {
            String query = "SELECT * From eventi WHERE Email LIKE " + "'" + email + "'";
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                risultato.add(rs.getString("titolo")+" "+rs.getString("data_evento"));
            }
        } catch (SQLException e) {
            System.out.println("Errore SQL " + e.getMessage());
        }
        return risultato;

    }
}
