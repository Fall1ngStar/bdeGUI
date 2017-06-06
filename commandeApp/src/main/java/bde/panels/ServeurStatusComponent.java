package bde.panels;

import bde.actions.SetStatusAction;
import bde.models.Serveur;
import bde.models.StatusServeur;
import bde.panels.ServeurPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 *  Conteneur permettant d'afficher et de modifier le status d'un serveur
 */
public class ServeurStatusComponent extends JPanel {

    // Le serveur à afficher et modifier
    private Serveur serveur;

    // TextField contenant le nom du serveur
    // TODO: changer le nom du serveur quand ce textfield est modifié
    private JTextField nomServeur;

    // Objets permettant la sélection et l'affichage du status du serveur
    private ButtonGroup statusServeurGroup;
    private JRadioButton ordi, sandwich, commis, nservice;

    private ServeurPanel parent;

    public ServeurStatusComponent(Serveur serveur, ServeurPanel parent) {
        this.serveur = serveur;
        this.parent = parent;
        initComponents();
        build();
    }

    /**
     * Initialise les différents éléments à afficher
     */
    private void initComponents() {
        nomServeur = new JTextField(serveur.getNom());
        statusServeurGroup = new ButtonGroup();

        ordi = new JRadioButton(new SetStatusAction("Ordi", StatusServeur.ORDI, this));
        sandwich = new JRadioButton(new SetStatusAction("Sandwichs", StatusServeur.SANDWICH, this));
        commis = new JRadioButton(new SetStatusAction("Commis", StatusServeur.COMMIS, this));
        nservice = new JRadioButton(new SetStatusAction("Pas en service", StatusServeur.HS, this));
    }

    /**
     * Organise les éléments dans le conteneur
     */
    private void build() {
        nomServeur.setColumns(15);
        add(nomServeur);
        statusServeurGroup.add(ordi);
        statusServeurGroup.add(commis);
        statusServeurGroup.add(sandwich);
        statusServeurGroup.add(nservice);

        JRadioButton statusServeurButton = null;
        switch (serveur.getStatus()) {
            case HS:
                statusServeurButton = nservice;
                break;
            case ORDI:
                statusServeurButton = ordi;
                break;
            case COMMIS:
                statusServeurButton = commis;
                break;
            case SANDWICH:
                statusServeurButton = sandwich;
                break;
        }
        statusServeurGroup.setSelected(statusServeurButton.getModel(), true);
        updateColor();
        add(nservice);
        add(ordi);
        add(commis);
        add(sandwich);

    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public Serveur getServeur() {
        return serveur;
    }

    @Override
    public ServeurPanel getParent() {
        return parent;
    }

    /**
     * Met à jour la couleur de fond en fonction du status du serveur
     */
    public void updateColor() {
        switch (serveur.getStatus()) {
            case COMMIS:
                setBackground(Color.RED);
                break;
            case ORDI:
                setBackground(Color.YELLOW);
                break;
            case HS:
                setBackground(Color.GRAY);
                break;
            case SANDWICH:
                setBackground(Color.GREEN);
                break;
        }
    }
}
