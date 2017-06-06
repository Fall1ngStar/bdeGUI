package bde.menus;

import bde.Config;

import javax.swing.*;

/**
 * CommandeAppMenuBar class
 * <p>
 * Barre de menu en haut de la fenêtre principale
 */
public class CommandeAppMenuBar extends JMenuBar {

    // Les différents onglets de la barre de menu
    private JMenu serveurs, commandes, stocks, others;

    // La checkbox pour activer/désactiver le tri automatique
    private JCheckBoxMenuItem autoSortServeurs;

    public CommandeAppMenuBar() {
        initComponents();
        build();
        createInterractions();
    }

    /**
     * Initialise les composants de la barre de menu
     */
    private void initComponents() {
        serveurs = new JMenu("Serveurs");
        commandes = new JMenu("Commandes");
        stocks = new JMenu("Stocks");
        others = new JMenu("?");

        autoSortServeurs = new JCheckBoxMenuItem("Trier automatiquement les serveurs");
    }

    /**
     * Construit et organise la barre de menu
     */
    private void build() {
        add(serveurs);
        add(commandes);
        add(stocks);
        add(others);

        serveurs.add(autoSortServeurs);
    }

    /**
     * Définit les actions/interractions des différents éléments des menus
     */
    private void createInterractions() {
        autoSortServeurs.addActionListener(e -> {
            Config.getInstance().serveurConfig.autoSortServeurs = autoSortServeurs.getState();
        });
    }

}
