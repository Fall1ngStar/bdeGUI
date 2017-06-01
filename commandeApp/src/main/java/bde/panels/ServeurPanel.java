package bde.panels;

import bde.Config;
import bde.ServeurStatusComponent;
import bde.models.Serveur;
import bde.models.StatusServeur;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ServeurPanel class
 * Created by Thierry
 * 31/05/2017
 */
public class ServeurPanel extends JPanel {

    private List<ServeurStatusComponent> listeServeurControls;

    private Serveur[] serveurs = {new Serveur("Serveur 1"), new Serveur("Serveur 2"), new Serveur("Serveur 3"), new Serveur("Serveur 4"), new Serveur("Serveur 5"), new Serveur("Serveur 6")};

    public ServeurPanel() {
        serveurs[2].setStatus(StatusServeur.SANDWICH);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        listeServeurControls = new ArrayList<>();
        for (Serveur s : serveurs) {
            listeServeurControls.add(new ServeurStatusComponent(s, this));
        }
        listeServeurControls.forEach(e -> ServeurPanel.this.add(e));
    }


    public void updateServeurListOrder(){
        if (Config.getInstance().serveurConfig.autoSortServeurs) {
            removeAll();
            listeServeurControls.sort(Comparator.comparing(ServeurStatusComponent::getServeur));
            listeServeurControls.forEach(e -> ServeurPanel.this.add(e));
        }
    }
}
