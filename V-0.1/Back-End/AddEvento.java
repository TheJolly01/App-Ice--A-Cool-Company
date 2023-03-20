
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class AddEvento extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JComboBox<String> oraInizioCombo;
    private JLabel jcomp5;
    private JDateChooser dataEvento;
    private JComboBox<String> oraFineCombo;
    private JLabel jcomp7;
    private JTextArea descrizioneTextArea;
    private JTextField titoloJtextField;
    private JButton creaEventoBtn;
    private JButton indietroBtn;

    public JComboBox<String> getOraInizioCombo() {
        return oraInizioCombo;
    }

    public JComboBox<String> getOraFineCombo() {
        return oraFineCombo;
    }

    public JTextArea getDescrizioneTextArea() {
        return descrizioneTextArea;
    }

    public JTextField getTitoloJtextField() {
        return titoloJtextField;
    }

    public JButton getCreaEventoBtn() {
        return creaEventoBtn;
    }

    public JButton getIndietroBtn() {
        return indietroBtn;
    }

    public JDateChooser getDataEvento() {
        return dataEvento;
    }

    public AddEvento() {
        // construct preComponents
        String[] oraItems = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };

        // construct components
        jcomp1 = new JLabel("Titolo");
        jcomp2 = new JLabel("Data Evento");
        jcomp3 = new JLabel("Ora inizio");
        oraInizioCombo = new JComboBox<String>(oraItems);
        jcomp5 = new JLabel("Ora Fine");
        oraFineCombo = new JComboBox<String>(oraItems);
        jcomp7 = new JLabel("Descrizione:");
        descrizioneTextArea = new JTextArea(5, 5);
        titoloJtextField = new JTextField(5);
        creaEventoBtn = new JButton("Crea Evento");
        indietroBtn = new JButton("Indietro");
        dataEvento = new JDateChooser();

        // adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        // add components
        add(jcomp1);
        add(jcomp2);
        add(jcomp3);
        add(oraInizioCombo);
        add(jcomp5);
        add(oraFineCombo);
        add(jcomp7);
        add(descrizioneTextArea);
        add(titoloJtextField);
        add(creaEventoBtn);
        add(indietroBtn);
        add(dataEvento);

        // set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds(125, 95, 35, 45);
        jcomp2.setBounds(90, 140, 75, 45);
        jcomp3.setBounds(105, 190, 65, 40);
        oraInizioCombo.setBounds(195, 190, 65, 35);
        jcomp5.setBounds(105, 235, 75, 35);
        oraFineCombo.setBounds(195, 235, 65, 35);
        jcomp7.setBounds(95, 300, 80, 55);
        descrizioneTextArea.setBounds(180, 320, 275, 80);
        titoloJtextField.setBounds(195, 95, 235, 30);
        creaEventoBtn.setBounds(175, 435, 165, 40);
        indietroBtn.setBounds(690, 45, 135, 35);
        dataEvento.setBounds(195, 150, 235, 30);
    }

    public static void apriCreaEvento(String email) {
        AddEvento addEvento = new AddEvento();
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(addEvento);
        frame.pack();
        frame.setVisible(true);
        addEvento.getIndietroBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> appuntTemp = ControlloLogin.stampaSavedAppuntamenti(email);
                AppuntamentiListPage.apriAppuntamentiList(appuntTemp, email);
                frame.dispose();
            }

        });

        addEvento.getCreaEventoBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String titoloTemp = addEvento.getTitoloJtextField().getText();
                String descrizionteTemp = addEvento.getDescrizioneTextArea().getText();
                Date date = addEvento.getDataEvento().getDate();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                String strDate = dateFormat.format(date);
                int oraInizioTemp = Integer.parseInt((String) addEvento.getOraInizioCombo().getSelectedItem());
                int oraFineTemp = Integer.parseInt((String) addEvento.getOraFineCombo().getSelectedItem());
                System.out.println(oraInizioTemp + " " + oraFineTemp);
                Boolean errore = true;
                errore = oraFineTemp <= oraInizioTemp || ControlloDati.isInputValid(titoloTemp);
                if (!errore) {
                    PopolamentoDB.creaEvento(email, titoloTemp, descrizionteTemp, strDate, oraInizioTemp, oraFineTemp);
                    JOptionPane.showMessageDialog(frame, "Evento Inserito", "Conferma",
                            JOptionPane.INFORMATION_MESSAGE);
                    ArrayList<String> appuntTemp = ControlloLogin.stampaSavedAppuntamenti(email);
                    AppuntamentiListPage.apriAppuntamentiList(appuntTemp, email);
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(frame, "Controllo i campi e riprova", "Conferma",
                            JOptionPane.ERROR_MESSAGE);
                }

            }

        });
    }
}
