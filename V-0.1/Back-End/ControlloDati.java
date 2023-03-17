import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.SQLException;

public class ControlloDati {
    public static Scanner stringhe = new Scanner(System.in);
    public static Scanner interi = new Scanner(System.in);

    public static void main(String[] args) {
        int i = controlloNumeri("Ciao");
        System.out.println(i);
    }

    public static String controlloStringhe(String text) {
        boolean b = true;
        while (b) {
            System.out.println(text);
            String input = stringhe.nextLine();
            if (input.length() == 0) {
                System.out.println("Il campo non può essere vuoto");
            } else {
                b = false;
                return input;
            }
        }
        return null;
    }

    public static int controlloNumeri(String text) {
        boolean errore = true;
        int i = 0;
        do {
            try {
                System.out.println(text);
                i = interi.nextInt();
                errore = false;
                if (i > 24) {
                    errore = true;
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("ERRORE");
                interi.nextLine();
                errore = true;
            } finally {
            }
        } while (errore);
        return i;
    }

    public static void controlloEinserimentoUtenti() {

        String email = controlloStringhe("Inserisci email");

        String query = "SELECT * From utenti WHERE email LIKE ?";
        try {
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            PreparedStatement stm = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            stm.setString(0, email);
            ResultSet rs = stm.executeQuery(query);
            if (!rs.next()) {
                String password = controlloStringhe("Inserisci password");
                String nome = controlloStringhe("Inserisci Nome");
                String cognome = controlloStringhe("Inserisci Cognome");
                PopolamentoDB.creaUtente(email, password, nome, cognome);
            }

        } catch (SQLException e) {
            System.out.println("Errore SQL" + e);
        }

    }

    public static void controlloEinserimentoEventi() {

        String email = controlloStringhe("Inserisci email");
        String titolo = controlloStringhe("Inserisci titolo");
        System.out.println("Inserisci descrizione");
        String descrizione = stringhe.nextLine();
        String dataEvento = controlloStringhe("Inserisci data Evento");
        int ora_inizio = controlloNumeri("Inserisci ora inizio evento");
        int ora_fine = controlloNumeri("Inserisci ora fine evento");

        PopolamentoDB.creaEvento(email, titolo, descrizione, dataEvento, ora_inizio, ora_fine);

    }
}