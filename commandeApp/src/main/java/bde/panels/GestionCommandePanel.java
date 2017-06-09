package bde.panels;

import bde.ListeCommandeServeur;

import javax.swing.*;
import java.awt.*;

/**
 * GestionCommandePanel class
 * <p>
 * Panel pour la gestion des commandes
 */
public class GestionCommandePanel extends JPanel {

    ListeCommandeServeur lcs1, lcs2, lcs3;

    public GestionCommandePanel() {
        intiComponents();
        build();
    }

    /**
     * Initialise les composants à afficher
     */
    private void intiComponents() {
    }

    /**
     * Organise les composants à afficher
     */
    private void build() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.01;
        c.weighty = 1;
        c.gridx = 0;
        c.anchor = GridBagConstraints.LINE_START;
        lcs1 = new ListeCommandeServeur(this);
        lcs1.setAlignmentY(TOP_ALIGNMENT);
        add(lcs1, c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;
        lcs2 = new ListeCommandeServeur(this);
        lcs2.setAlignmentY(TOP_ALIGNMENT);
        add(lcs2, c);
        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_END;
        lcs3 = new ListeCommandeServeur(this);
        lcs3.setAlignmentY(TOP_ALIGNMENT);
        add(lcs3, c);

    }

    public ListeCommandeServeur[] getServeursListes(){
        return new ListeCommandeServeur[]{lcs1,lcs2,lcs3};
    }

}
