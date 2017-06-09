package bde.panels;

import bde.Config;
import bde.Manager;
import bde.models.Serveur;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ServeurPanel class
 * <p>
 * Panel de gestion des serveurs
 */
public class ServeurPanel extends JPanel {

    private List<ServeurStatusComponent> listeServeurControls;

    private Serveur[] serveurs = {new Serveur("Serveur 1"), new Serveur("Serveur 2"), new Serveur("Serveur 3"), new Serveur("Serveur 4"), new Serveur("Serveur 5"), new Serveur("Serveur 6")};

    public ServeurPanel() {
        build();
    }

    /**
     * Organise les composants
     */
    private void build() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        listeServeurControls = new ArrayList<>();
        for (Serveur s : Manager.getInstance().getServeurs()) {
            listeServeurControls.add(new ServeurStatusComponent(s, this));
        }
        listeServeurControls.forEach(e -> ServeurPanel.this.add(e));
    }


    /**
     * Met à jour l'ordre des serveurs en fonction de leur rôle
     */
    public void updateServeurListOrder() {
        if (Config.getInstance().serveurConfig.autoSortServeurs) {
            removeAll();
            listeServeurControls.sort(Comparator.comparing(ServeurStatusComponent::getServeur));
            listeServeurControls.forEach(e -> ServeurPanel.this.add(e));
        }
    }
}
