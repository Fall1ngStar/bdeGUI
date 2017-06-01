package bde.panels;

import bde.Commande;
import bde.CommandeContainerPanel;

import javax.swing.*;
import java.util.ArrayList;

/**
 * GestionCommandePanel class
 * Created by Thierry
 * 31/05/2017
 */
public class GestionCommandePanel extends JPanel {

    CommandeContainerPanel commandeContainer;
    Commande commande;

    public GestionCommandePanel() {
        intiComponents();
        build();
    }

    private void intiComponents(){
        ArrayList<String> a = new ArrayList<>();
        a.add("Panini");
        a.add("Poulet jambon");
        commande = new Commande(a);
        commandeContainer = new CommandeContainerPanel(commande);
    }

    private void build(){
        add(commandeContainer);
    }
}
