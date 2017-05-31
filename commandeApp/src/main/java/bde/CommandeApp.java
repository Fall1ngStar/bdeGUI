package bde;

import javax.swing.*;

/**
 * CommandeApp class
 * Created by Thierry
 * 31/05/2017
 */
public class CommandeApp {
    public static void main(String[] args){
        SwingUtilities.invokeLater(CommandeAppFenetre::new);
    }
}
