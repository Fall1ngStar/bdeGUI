package bde;

import bde.models.Commande;
import bde.models.Serveur;
import bde.panels.CommandeContainerPanel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager class
 * Created by Thierry
 * 09/06/2017
 */
public class Manager {

    private static Manager instance = new Manager();

    private List<Serveur> serveurs;
    private List<Commande> commandes;
    private Map<ListeCommandeServeur, List<CommandeContainerPanel>> listesCommandes;

    private Manager(){
        serveurs = new ArrayList<>();
        commandes = new ArrayList<>();
        listesCommandes = new HashMap<>();
        init();
    }

    public static Manager getInstance() {
        return instance;
    }

    private void init(){
        serveurs.addAll(ConnexionBDD.getInstance().getServeurs());
    }

    public List<Serveur> getServeurs() {
        return serveurs;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public Map<ListeCommandeServeur, List<CommandeContainerPanel>> getListesCommandes() {
        return listesCommandes;
    }
}
