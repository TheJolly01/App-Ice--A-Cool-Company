import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginPage {
    public static void main(String[] args) {

        JFrame loginPage = new JFrame("ICETIME s.p.a");
        JButton login = new JButton("Login");
        JButton BtnRegistra = new JButton("Registra");
        JTextField emailInput = new JTextField();
        JTextField passInput = new JPasswordField();
        JLabel emailLabel = new JLabel("Inserisci email");
        JLabel passLabel = new JLabel("Inserisci password:");

        loginPage.setPreferredSize(new Dimension(600, 350));
        loginPage.setLayout(null);
        loginPage.add(login);
        loginPage.add(emailInput);
        loginPage.add(emailLabel);
        loginPage.add(passInput);
        loginPage.add(passLabel);
        loginPage.add(BtnRegistra);

        login.setBounds(250, 200, 85, 35);

        BtnRegistra.setBounds(250, 250, 85, 35);

        passInput.setBounds(230, 170, 195, 25);
        emailInput.setBounds(230, 140, 195, 25);
        emailLabel.setBounds(100, 140, 150, 25);
        passLabel.setBounds(100, 170, 150, 25);

        loginPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginPage.pack();
        loginPage.setVisible(true);
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Controllo Dati e apertura calendario
                String emailTemp = emailInput.getText();
                String passTemp = passInput.getText();
                boolean controllo = ControlloLogin.autenticazioneAccesso(emailTemp, passTemp);
                if (controllo) {
                    ArrayList<String> appuntTemp = ControlloLogin.stampaSavedAppuntamenti(emailTemp);
                    loginPage.dispose();
                    AppuntamentiListPage.apriAppuntamentiList(appuntTemp, emailTemp);
                } else {
                    JOptionPane.showMessageDialog(loginPage, "Email o password Errati ripova", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }

        });

        BtnRegistra.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Apertura Pagina Registrazione
                loginPage.dispose();
                RegistraPage.apriFrameRegistra();

            }

        });

    }
}
