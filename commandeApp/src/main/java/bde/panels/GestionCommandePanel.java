package bde.panels;

import bde.Commande;
import bde.CommandeContainerPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GestionCommandePanel class
 * Created by Thierry
 * 31/05/2017
 */
public class GestionCommandePanel extends JPanel {

    List<CommandeContainerPanel> commandeContainer;
    List<Commande> commande;

    public GestionCommandePanel() {
        intiComponents();
        build();
    }

    private void intiComponents(){
        ArrayList<String> a = new ArrayList<>();
        a.add("Panini");
        a.add("Poulet jambon");

        commandeContainer = new ArrayList<>();
        commande = new ArrayList<>();

        for(int i = 0; i < 12; i++){
            commandeContainer.add(new CommandeContainerPanel(new Commande(a)));
        }
    }

    private void build(){
        commandeContainer.forEach(GestionCommandePanel.this::add);
    }
}
