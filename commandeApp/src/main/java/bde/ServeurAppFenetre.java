package bde;

import bde.panels.ServeurAppPanel;

import javax.swing.*;

/**
 * Created by Justine on 09/06/2017.
 */
public class ServeurAppFenetre extends JFrame {


    public ServeurAppFenetre() {
        setTitle("Commandes en cours");
        setSize(1280, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new ServeurAppPanel());
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
