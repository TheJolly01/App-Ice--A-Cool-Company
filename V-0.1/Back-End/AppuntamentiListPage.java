
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JList;
import java.util.ArrayList;


public class AppuntamentiListPage extends JPanel {
    private JLabel titleAppu;
    private JList<String> appuntamentiList;
    private JButton logoutBt;
    private JButton creaAppBt;

    public AppuntamentiListPage(ArrayList<String> savedAppuntamenti) {

        // construct preComponents

        // construct components
        titleAppu = new JLabel("  I tuoi Appuntamenti:");
        appuntamentiList = new JList<String>(savedAppuntamenti.toArray(String[]::new));
        logoutBt = new JButton("Logout");
        creaAppBt = new JButton("Crea");

        // adjust size and set layout
        setPreferredSize(new Dimension(600, 439));
        setLayout(null);

        // add components
        add(titleAppu);
        add(appuntamentiList);
        add(logoutBt);
        add(creaAppBt);

        // set component bounds (only needed by Absolute Positioning)
        titleAppu.setBounds(115, 90, 130, 35);
        appuntamentiList.setBounds(120, 120, 400, 180);
        logoutBt.setBounds(330, 35, 100, 25);
        creaAppBt.setBounds(30, 335, 100, 25);
    }

    public JButton getlogoutBt() {
        return logoutBt;
    }

    public JButton getCreaAppbt() {
        return creaAppBt;
    }



    public static void apriAppuntamentiList(ArrayList<String> savedAppu, String email) {
        AppuntamentiListPage appuntamentiPage=new AppuntamentiListPage(savedAppu);
        JFrame frame = new JFrame("MyPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(appuntamentiPage);
        frame.pack();
        frame.setVisible(true);
        appuntamentiPage.getlogoutBt().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginPage.main(null);
            }
            
        });

    appuntamentiPage.getCreaAppbt().addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            AddEvento.apriCreaEvento(email);
        }
        
    });
    }
}