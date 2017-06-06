package bde;

import bde.menus.CommandeAppMenuBar;
import bde.panels.CommandePanelPrincipal;

import javax.swing.*;

/**
 * CommandeAppFenetre class
 *
 * Fenêtre principale de l'application
 */
public class CommandeAppFenetre extends JFrame {

    public CommandeAppFenetre() {
        build();
    }

    /**
     * Organise les éléments et définit les propriétés de la fenêtre
     */
    private void build() {
        setTitle("Application commandes");
        setSize(1280, 1024);
        setContentPane(new CommandePanelPrincipal());
        setJMenuBar(new CommandeAppMenuBar());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
