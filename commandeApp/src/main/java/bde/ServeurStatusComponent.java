package bde;

import bde.actions.SetStatusAction;
import bde.models.Serveur;
import bde.models.StatusServeur;
import bde.panels.ServeurPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Created by Thierry on 31/05/2017.
 */
public class ServeurStatusComponent extends JPanel {

    private Serveur serveur;

    private JTextField nomServeur;
    private ButtonGroup statusServeurGroup;
    private JRadioButton ordi, sandwich, commis, nservice;

    private ServeurPanel parent;

    public ServeurStatusComponent(Serveur serveur, ServeurPanel parent) {
        this.serveur = serveur;
        this.parent = parent;
        initComponents();
        build();
    }

    private void initComponents() {
        nomServeur = new JTextField(serveur.getNom());
        statusServeurGroup = new ButtonGroup();

        ordi = new JRadioButton(new SetStatusAction("Ordi", StatusServeur.ORDI,this));
        sandwich = new JRadioButton(new SetStatusAction("Sandwichs",StatusServeur.SANDWICH,this));
        commis = new JRadioButton(new SetStatusAction("Commis",StatusServeur.COMMIS,this));
        nservice = new JRadioButton(new SetStatusAction("Pas en service",StatusServeur.HS,this));
    }

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

        nomServeur.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println(nomServeur.getText());
            }
        });
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
