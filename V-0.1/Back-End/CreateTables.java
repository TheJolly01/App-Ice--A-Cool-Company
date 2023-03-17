import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
    public static void main(String[] args) {
        createDB();
        createTbUtenti();
        createTbEventi();
        createTbTodoList();
    }

    public static void createDB() {
        String query = "CREATE DATABASE IceTime;";
        try {
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/", "root", "root");
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
            System.out.println("Database Creato con successo");

        } catch (SQLException e) {
            System.out.println("Creazione Database Fallita " + e);
        }
    }

    public static void createTbUtenti() {
        String query = "CREATE TABLE utenti(email varchar(100) primary key, Password varchar(100) not null , nome varchar(100) not null , cognome varchar(100) not null);";
        try {
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            Statement stm = conn.createStatement();
            System.out.println("Tabella Utenti creata con successo");
            stm.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Creazione tabella Fallita " + e);
        }
    }

    public static void createTbEventi() {
        String query = "CREATE TABLE Eventi "
                + "(id int auto_increment primary key, "
                + "email varchar(100) not null, "
                + "titolo varchar(50) not null, "
                + "descrizione_evento varchar(255), "
                + "data_evento varchar(50) not null, "
                + "ora_inizio int not null, "
                + "ora_fine int not null, "
                + "foreign key (email) references utenti(email)); ";
        try {
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
            System.out.println("Tabella Eventi creata con successo");

        } catch (SQLException e) {
            System.out.println("Creazione tabella Fallita " + e);
        }
    }

    public static void createTbTodoList() {
        String query = "CREATE TABLE ToDoList "
                + "(id int auto_increment primary key, "
                + "email varchar(100) not null, "
                + "titolo varchar(50) not null, "
                + "descrizione varchar(255), "
                + "data_evento varchar(50) not null, "
                + "isChecked boolean not null, "
                + "foreign key (email) references utenti(email)); ";
        try {
            Connection conn = ConnectionDB.createConnection("jdbc:mysql://localhost:3306/IceTime", "root", "root");
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
            System.out.println("Tabella ToDoList creata con successo");

        } catch (SQLException e) {
            System.out.println("Creazione tabella Fallita " + e);
        }
    }
}
