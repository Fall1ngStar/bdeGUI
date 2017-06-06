package bde;

import javax.swing.*;

/**
 * CommandeApp class
 *
 * Point d'entrée du programme
 */
public class CommandeApp {
    public static void main(String[] args){
        SwingUtilities.invokeLater(CommandeAppFenetre::new);
    }
}
