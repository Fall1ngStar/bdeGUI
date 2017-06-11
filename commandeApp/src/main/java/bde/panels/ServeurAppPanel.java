package bde.panels;

import bde.Manager;
import bde.ManagerEvent;
import bde.ManagerObserver;
import bde.models.Serveur;

import javax.swing.*;

/**
 * Created by Thierry on 11/06/2017.
 */
public class ServeurAppPanel extends JPanel implements ManagerObserver {

    public ServeurAppPanel() {
        Manager.getInstance().addObserver(this);
        add(new JLabel("TEst"));
        System.out.println(getComponents().length);
        setVisible(true);
    }

    @Override
    public void handleEvent(ManagerEvent e) {
        reloadAllCommandes();
    }

    private void reloadAllCommandes() {
        ServeurAppPanel.this.removeAll();
        Manager.getInstance().getCommandes().forEach(e -> ServeurAppPanel.this.add(new CommandeContainerPanel(e)));
        repaint();
        revalidate();
    }
}
