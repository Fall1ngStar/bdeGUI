package bde.panels;

import bde.CommandeContainerContextMenu;
import bde.models.Commande;

import javax.swing.*;
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
                            Commande c = wizard.getCommande();
                            if (c != null) {
                                GestionCommandePanel.this.addCommande(c);
                            }
                        }
                    }
            );
        });
        add(createCommande);
    }

    /**
     * Ajoute une commande dans un {@link CommandeContainerPanel CommandeContainerPanel} au panel
     * @param c la commande à ajouter
     */
    private void addCommande(Commande c) {
        final CommandeContainerPanel ccp = new CommandeContainerPanel(c);
        ccp.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e){
                JPopupMenu context = new CommandeContainerContextMenu(GestionCommandePanel.this,ccp);
                if(e.isPopupTrigger()){
                    context.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        add(ccp);
        repaint();
        revalidate();
    }
}
