
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegistraPage extends JPanel {
    private JLabel emailLabel;
    private JLabel passwordLabel;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JTextField nomeTextField;
    private JTextField cognomeTextField;
    private JButton registraBtn;
    private JButton backBtn;

    public String getNomeTextField() {
        return nomeTextField.getText();
    }

    public String getCognomeTextField() {
        return cognomeTextField.getText();
    }

    public String getEmailString() {
        return emailTextField.getText();
    }

    public String getPasswordTextField() {
        return passwordTextField.getText();
    }

    public JButton getBackBtn() {
        return backBtn;

    }

    public JButton getRegistraBtn() {
        return registraBtn;

    }

    public RegistraPage() {
        // construct components
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("Password:");
        nomeLabel = new JLabel("Nome:");
        cognomeLabel = new JLabel("Cognome:");
        emailTextField = new JTextField(5);
        passwordTextField = new JTextField(5);
        nomeTextField = new JTextField(5);
        cognomeTextField = new JTextField(5);
        registraBtn = new JButton("Registra");
        backBtn = new JButton("Indietro");

        // adjust size and set layout
        setPreferredSize(new Dimension(459, 382));
        setLayout(null);

        // add components
        add(emailLabel);
        add(passwordLabel);
        add(nomeLabel);
        add(cognomeLabel);
        add(emailTextField);
        add(passwordTextField);
        add(nomeTextField);
        add(cognomeTextField);
        add(registraBtn);
        add(backBtn);

        // set component bounds (only needed by Absolute Positioning)
        emailLabel.setBounds(100, 105, 80, 25);
        passwordLabel.setBounds(100, 135, 80, 25);
        nomeLabel.setBounds(100, 170, 80, 25);
        cognomeLabel.setBounds(100, 200, 85, 25);
        emailTextField.setBounds(185, 105, 100, 25);
        passwordTextField.setBounds(185, 135, 100, 25);
        nomeTextField.setBounds(185, 170, 100, 25);
        cognomeTextField.setBounds(185, 200, 100, 25);
        registraBtn.setBounds(180, 240, 100, 25);
        backBtn.setBounds(345, 15, 100, 25);
    }

    public static void apriFrameRegistra() {
        RegistraPage registraPage = new RegistraPage();
        JFrame frame = new JFrame("Registrazione");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(registraPage);
        frame.pack();
        frame.setVisible(true);
        registraPage.getBackBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginPage.main(null);
            }

        });

        registraPage.getRegistraBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String emailTemp = registraPage.getEmailString();
                String passTemp = registraPage.getPasswordTextField();
                String nomeTemp = registraPage.getNomeTextField();
                String cognTemp = registraPage.getCognomeTextField();
                boolean errore = true;
                errore = ControlloDati.isInputValid(emailTemp) || ControlloDati.isInputValid(passTemp) || ControlloDati.isInputValid(nomeTemp) || ControlloDati.isInputValid(cognTemp);
                if (!errore) {
                    PopolamentoDB.creaUtente(emailTemp, passTemp, nomeTemp, cognTemp);
                    JOptionPane.showMessageDialog(frame, "Registrazione Completata", "Conferma",
                            JOptionPane.INFORMATION_MESSAGE);
                    LoginPage.main(null);
                    frame.dispose();
                } else {
                    System.out.println("Errore");
                    JOptionPane.showMessageDialog(frame, "Controlla i campi e riporva", "ERRORE",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });

    }
}