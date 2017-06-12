package bde;

import bde.models.Commande;
import bde.models.Serveur;
import bde.panels.CommandeContainerPanel;
import bde.panels.CommandeWizard;
import bde.panels.GestionCommandePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Thierry on 08/06/2017.
 */
public class ListeCommandeServeur extends JPanel {

    private JLabel serveurName;
    private JButton createCommande;
    private GestionCommandePanel parent;

    private Serveur serveur;
    private int col;

    public ListeCommandeServeur(GestionCommandePanel parent, int col, Serveur serveur){
        this.parent = parent;
        this.col = col;
        this.serveur = serveur;
        initComponents();
        build();
        createInterractions();
    }
    private void initComponents(){
        serveurName = new JLabel(serveur != null ? serveur.getNom() : "Serveur");
        createCommande = new JButton("Nouvelle commande");
    }

    private void build(){
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        add(Box.createRigidArea(new Dimension(0,5)));
        serveurName.setAlignmentX(CENTER_ALIGNMENT);
        add(serveurName);
        add(Box.createRigidArea(new Dimension(0,5)));
        createCommande.setAlignmentX(CENTER_ALIGNMENT);
        add(createCommande);
        add(Box.createRigidArea(new Dimension(0,10)));

    }

    private void createInterractions(){
        createCommande.addActionListener(e -> {
            CommandeWizard wizard = new CommandeWizard();
            wizard.addWindowListener(
                    new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // A la fermeture de la fenêtre de création de commande, récupère la commande crée
                            Commande c = wizard.getCommande();
                            if (c != null) {
                                Manager.getInstance().addCommande(c,col);
                                ListeCommandeServeur.this.addCommande(c);
                            }
                        }
                    }
            );
        });
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
                JPopupMenu context = new CommandeContainerContextMenu(ListeCommandeServeur.this,ccp, parent.getServeursListes());
                if(e.isPopupTrigger()){
                    context.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        add(ccp);
        repaint();
        revalidate();
    }

    public void updateServeurName(Serveur s){
        serveur = s;
        serveurName.setText(s != null ? s.getNom() : "Serveur");
    }
}
