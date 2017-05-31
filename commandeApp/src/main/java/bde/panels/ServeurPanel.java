package bde.panels;

import bde.models.Serveur;
import bde.actions.SetStatusAction;
import bde.models.StatusServeur;
import bde.models.ServeurListModel;
import bde.renderers.ServeurComboRenderer;
import bde.renderers.ServeurListRenderer;

import javax.swing.*;

/**
 * ServeurPanel class
 * Created by Thierry
 * 31/05/2017
 */
public class ServeurPanel extends JPanel {

    private JList<Serveur> listeServeursActifs;
    private JComboBox<Serveur> comboServeurs;
    private ButtonGroup statusServeur;
    private JRadioButton ordi, sandwich, commis, nservice;

    private Serveur[] serveurs = {new Serveur("Serveur 1"),new Serveur("Serveur 2"),new Serveur("Serveur 3"),new Serveur("Serveur 4"),new Serveur("Serveur 5"),new Serveur("Serveur 6")};

    public ServeurPanel(){
        serveurs[2].setStatus(StatusServeur.SANDWICH);
        initComponents();
        build();
        createInterractions();
    }

    private void initComponents(){
        listeServeursActifs = new JList<>(new ServeurListModel(serveurs));
        comboServeurs = new JComboBox<>(serveurs);
        statusServeur = new ButtonGroup();
        ordi = new JRadioButton(new SetStatusAction("Ordi",StatusServeur.ORDI, this));
        sandwich = new JRadioButton(new SetStatusAction("Sandwichs",StatusServeur.SANDWICH,this));
        commis = new JRadioButton(new SetStatusAction("Commis",StatusServeur.COMMIS,this));
        nservice = new JRadioButton(new SetStatusAction("Pas en service",StatusServeur.HS,this));
    }

    private void build(){
        add(listeServeursActifs);
        add(comboServeurs);

        statusServeur.add(ordi);
        statusServeur.add(commis);
        statusServeur.add(sandwich);
        statusServeur.add(nservice);
        statusServeur.setSelected(nservice.getModel(), true);

        add(ordi);
        add(commis);
        add(sandwich);
        add(nservice);

        comboServeurs.setRenderer(new ServeurComboRenderer());
        listeServeursActifs.setCellRenderer(new ServeurListRenderer());
    }

    private void createInterractions(){
        comboServeurs.addItemListener(e->{
            StatusServeur statusServeur = ((Serveur)e.getItem()).getStatus();
            JRadioButton statusServeurButton = null;
            switch (statusServeur){
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
            this.statusServeur.setSelected(statusServeurButton.getModel(), true);
        });
    }

    public JComboBox<Serveur> getComboServeurs() {
        return comboServeurs;
    }

    public JList<Serveur> getListeServeursActifs() {
        return listeServeursActifs;
    }
}
