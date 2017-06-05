package bde.panels;

import bde.Commande;
import bde.CommandeContainerPanel;
import bde.CommandeWizard;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * GestionCommandePanel class
 * Created by Thierry
 * 31/05/2017
 */
public class GestionCommandePanel extends JPanel {

    private JButton createCommande;

    public GestionCommandePanel() {
        intiComponents();
        build();
    }

    private void intiComponents() {
        createCommande = new JButton("Ajouter une commande");
    }

    private void build() {
        createCommande.addActionListener(e -> {
            CommandeWizard wizard = new CommandeWizard();
            wizard.addWindowListener(
                    new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            GestionCommandePanel.this.addCommande(wizard.getCommande());
                        }
                    }
            );
        });
        add(createCommande);
    }

    private void addCommande(Commande c) {
        add(new CommandeContainerPanel(c));
        repaint();
        revalidate();
    }
}
