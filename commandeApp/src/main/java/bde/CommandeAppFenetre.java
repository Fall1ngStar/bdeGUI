package bde;

import javax.swing.*;

/**
 * CommandeAppFenetre class
 * Created by Thierry
 * 31/05/2017
 */
public class CommandeAppFenetre extends JFrame{

    public CommandeAppFenetre(){
        build();
    }

    private void build(){
        setTitle("Application commandes");
        setSize(1280,720);
        setContentPane(new CommandePanelPrincipal());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
