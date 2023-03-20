import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class PopolamentoDB {

    public static void creaUtente(String email, String password, String nome, String cognome) {
        String query = "SELECT * From utenti;";
        try {
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery(query);
            rs.moveToInsertRow();
            rs.updateString("email", email);
            rs.updateString("password", password);
            rs.updateString("nome", nome);
            rs.updateString("cognome", cognome);
            rs.insertRow();
            rs.moveToCurrentRow();
            System.out.println("Utente inserito");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void creaEvento(String email, String titolo, String descrizione, String dataEvento, int ora_inizio,
            int ora_fine) {
        String query = "SELECT * From eventi;";
        try {
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery(query);
            rs.moveToInsertRow();
            rs.updateString("email", email);
            rs.updateString("titolo", titolo);
            rs.updateString("descrizione_evento", descrizione);
            rs.updateString("data_evento", dataEvento);
            rs.updateInt("ora_inizio", ora_inizio);
            rs.updateInt("ora_fine", ora_fine);
            rs.insertRow();
            rs.moveToCurrentRow();
            System.out.println("Evento inserito");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void creaToDoList(String email, String titolo, String descrizione, String dataEvento,
            boolean checked) {
        String query = "SELECT * From eventi;";
        try {
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery(query);
            rs.moveToInsertRow();
            rs.updateString("email", email);
            rs.updateString("titolo", titolo);
            rs.updateString("descrizione", descrizione);
            rs.updateString("data_evento", dataEvento);
            rs.updateBoolean("isChecked", checked);
            rs.insertRow();
            rs.moveToCurrentRow();
            System.out.println("Utente creato inserito");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
