package bde.panels;

import bde.CommandeContainerContextMenu;
import bde.ListeCommandeServeur;
import bde.models.Commande;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        /*ListeCommandeServeur lcs1 = new ListeCommandeServeur()
                , lcs2 = new ListeCommandeServeur(), lcs3 = new ListeCommandeServeur();
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(lcs1, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(lcs2, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(lcs3, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(lcs1, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(lcs2, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
                        .addComponent(lcs3, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        layout.linkSize(lcs1,lcs2,lcs3);*/
    }

    public ListeCommandeServeur[] getServeursListes(){
        return new ListeCommandeServeur[]{lcs1,lcs2,lcs3};
    }

}
