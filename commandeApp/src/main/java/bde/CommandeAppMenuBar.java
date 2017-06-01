package bde;

import javax.swing.*;

/**
 * CommandeAppMenuBar class
 * Created by Thierry
 * 01/06/2017
 */
public class CommandeAppMenuBar extends JMenuBar {

    private JMenu serveurs, commandes, stocks, others;

    private JCheckBoxMenuItem autoSortServeurs;

    public CommandeAppMenuBar() {
        initComponents();
        build();
        createInterractions();
    }

    private void initComponents() {
        serveurs = new JMenu("Serveurs");
        commandes = new JMenu("Commandes");
        stocks = new JMenu("Stocks");
        others = new JMenu("?");

        autoSortServeurs = new JCheckBoxMenuItem("Trier automatiquement les serveurs");
    }

    private void build() {
        add(serveurs);
        add(commandes);
        add(stocks);
        add(others);

        serveurs.add(autoSortServeurs);
    }

    private void createInterractions(){
        autoSortServeurs.addActionListener(e->{
            Config.getInstance().serveurConfig.autoSortServeurs = autoSortServeurs.getState();
        });
    }

}
