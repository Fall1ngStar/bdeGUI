package bde.panels;

import bde.models.Commande;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * GestionCommandePanel class
 * <p>
 * Panel pour la gestion des commandes
 */
public class GestionCommandePanel extends JPanel {

    private JButton createCommande;

    public GestionCommandePanel() {
        intiComponents();
        build();
    }

    /**
     * Initialise les composants à afficher
     */
    private void intiComponents() {
        createCommande = new JButton("Ajouter une commande");
    }

    /**
     * Organise les composants à afficher
     */
    private void build() {
        createCommande.addActionListener(e -> {
            CommandeWizard wizard = new CommandeWizard();
            wizard.addWindowListener(
                    new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // A la fermeture de la fenêtre de création de commande, récupère la commande crée
                            GestionCommandePanel.this.addCommande(wizard.getCommande());
                        }
                    }
            );
        });
        add(createCommande);
    }

    /**
     * Ajoute une commande dans un {@link CommandeContainerPanel CommandeContainerPanel} au panel
     *
     * @param c la commande à ajouter
     */
    private void addCommande(Commande c) {
        add(new CommandeContainerPanel(c));
        repaint();
        revalidate();
    }
}
