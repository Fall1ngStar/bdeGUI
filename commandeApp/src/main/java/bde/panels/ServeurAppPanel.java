package bde.panels;

import bde.Manager;
import bde.ManagerEvent;
import bde.ManagerObserver;
import bde.models.Commande;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thierry on 11/06/2017.
 */
public class ServeurAppPanel extends JPanel implements ManagerObserver {

    private Map<Commande, CommandeContainerPanel> commandes;

    public ServeurAppPanel() {
        Manager.getInstance().addObserver(this);
        commandes = new HashMap<>();
        setVisible(true);
    }

    @Override
    public void handleEvent(ManagerEvent e) {
        switch (e.getType()){
            case AJOUT_COMMANDE:
                commandes.put(e.getCommande(), new CommandeContainerPanel(e.getCommande()));
                commandes.get(e.getCommande()).hideButtons();
                add(commandes.get(e.getCommande()));
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
                        remove(commandes.get(e.getCommande()));
                        commandes.remove(e.getCommande());
                        repaint();
                        revalidate();
                        break;
                }
        }
    }
}
