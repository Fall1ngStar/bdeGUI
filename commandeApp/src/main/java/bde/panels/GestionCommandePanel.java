package bde.panels;

import bde.ListeCommandeServeur;
import bde.Manager;
import bde.ManagerEvent;
import bde.ManagerObserver;
import bde.models.Serveur;
import bde.models.StatusCommande;

import javax.swing.*;
import java.awt.*;

/**
 * GestionCommandePanel class
 * <p>
 * Panel pour la gestion des commandes
 */
public class GestionCommandePanel extends JPanel implements ManagerObserver {

    private ListeCommandeServeur lcs1, lcs2, lcs3;

    public GestionCommandePanel() {
        Manager.getInstance().addObserver(this);

        intiComponents();
        build();
    }

    /**
     * Initialise les composants à afficher
     */
    private void intiComponents() {
        Serveur[] serveurs = Manager.getInstance().getServeursSandwich();
        lcs1 = new ListeCommandeServeur(this, 0, serveurs[0]);
        lcs2 = new ListeCommandeServeur(this, 1, serveurs[1]);
        lcs3 = new ListeCommandeServeur(this, 2, serveurs[2]);
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

        lcs1.setAlignmentY(TOP_ALIGNMENT);
        add(lcs1, c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.CENTER;

        lcs2.setAlignmentY(TOP_ALIGNMENT);
        add(lcs2, c);
        c.gridx = 2;
        c.anchor = GridBagConstraints.LINE_END;

        lcs3.setAlignmentY(TOP_ALIGNMENT);
        add(lcs3, c);

    }

    public ListeCommandeServeur[] getServeursListes() {
        return new ListeCommandeServeur[]{lcs1, lcs2, lcs3};
    }

    @Override
    public void handleEvent(ManagerEvent e) {
        switch (e.getType()) {
            case COMMANDE_STATUS_CHANGE:
                if (e.getCommande().getStatus() == StatusCommande.REMISE) {
                    for (ListeCommandeServeur l : getServeursListes()) {
                        for (Component c : getComponents()) {
                            if (c instanceof ListeCommandeServeur) {
                                for (Component el : ((ListeCommandeServeur) c).getComponents()) {
                                    if (el instanceof CommandeContainerPanel) {
                                        if (((CommandeContainerPanel) el).getCommande() == e.getCommande()) {
                                            ((ListeCommandeServeur) c).remove(el);
                                            repaint();
                                            revalidate();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case SERVEUR_UPDATE:
                updateServeursName();
        }
    }

    public void updateServeursName() {
        Serveur[] serveurs = Manager.getInstance().getServeursSandwich();
        lcs1.updateServeurName(serveurs[0]);
        lcs2.updateServeurName(serveurs[1]);
        lcs3.updateServeurName(serveurs[2]);
    }
}
