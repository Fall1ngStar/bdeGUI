package bde.panels;

import bde.Manager;
import bde.ManagerEvent;
import bde.ManagerObserver;
import bde.models.Commande;
import bde.models.Serveur;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thierry on 11/06/2017.
 */
public class ServeurAppPanel extends JPanel implements ManagerObserver {

    private Map<Commande, CommandeContainerPanel> commandes;
    private JPanel[] panels;

    public ServeurAppPanel() {
        Manager.getInstance().addObserver(this);
        commandes = new HashMap<>();
        setVisible(true);
        panels = new JPanel[3];
        setLayout(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        for(int i = 0; i < 3; i++){
            c.gridx = i;
            panels[i] = new JPanel();
            panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.Y_AXIS));
            panels[i].add(new JLabel("Serveur " + (i+1)));
            ((JLabel)panels[i].getComponent(0)).setAlignmentX(CENTER_ALIGNMENT);
            panels[i].add(Box.createRigidArea(new Dimension(0,10)));
            add(panels[i], c);
        }
    }

    @Override
    public void handleEvent(ManagerEvent e) {
        switch (e.getType()){
            case AJOUT_COMMANDE:
                commandes.put(e.getCommande(), new CommandeContainerPanel(e.getCommande()));
                commandes.get(e.getCommande()).hideButtons();
                commandes.get(e.getCommande()).setAlignmentX(CENTER_ALIGNMENT);
                panels[e.getCol()].add(commandes.get(e.getCommande()));
                repaint();
                revalidate();
                break;
            case COMMANDE_STATUS_CHANGE:
                switch (e.getCommande().getStatus()){
                    case PRETE:
                        commandes.get(e.getCommande()).updateStatus();
                        break;
                    case REMISE:
                        System.out.println("Status remise");
                        for(JPanel p : panels){
                            for(Component ccp : p.getComponents()){
                                if(ccp == commandes.get(e.getCommande())){
                                    p.remove(ccp);
                                }
                            }
                        }
                        commandes.remove(e.getCommande());
                        repaint();
                        revalidate();
                        break;
                }
                break;
            case SERVEUR_UPDATE:
                Serveur[] serveurs = Manager.getInstance().getServeursSandwich();
                for(int i = 0; i < 3; i++){
                    ((JLabel)panels[i].getComponent(0)).setText(serveurs[i] != null ? serveurs[i].getNom() : "Serveur");
                }
        }
    }
}
