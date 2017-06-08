package bde;

import javax.swing.*;

/**
 * CommandeApp class
 *
 * Point d'entrée du programme
 */
public class CommandeApp {
    public static void main(String[] args){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
        }
        SwingUtilities.invokeLater(CommandeAppFenetre::new);
    }
}
