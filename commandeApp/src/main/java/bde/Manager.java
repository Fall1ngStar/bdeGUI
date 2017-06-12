package bde;

import bde.models.Commande;
import bde.models.Serveur;
import bde.models.StatusCommande;
import bde.models.StatusServeur;
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
    private Map<String, List<String>> ingredients;

    private List<ManagerObserver> observers;

    private Manager(){
        serveurs = new ArrayList<>();
        commandes = new ArrayList<>();
        listesCommandes = new HashMap<>();
        ingredients = new HashMap<>();
        observers = new ArrayList<>();
        init();
    }

    public static Manager getInstance() {
        return instance;
    }

    private void init(){
        serveurs.addAll(ConnexionBDD.getInstance().getServeurs());
        ingredients.put("Boisson", ConnexionBDD.getInstance().getListeElem("Boisson"));
        ingredients.put("Sauce", ConnexionBDD.getInstance().getListeElem("Sauce"));
        ingredients.put("Dessert", ConnexionBDD.getInstance().getListeElem("Dessert"));
        ingredients.put("Ingredient", ConnexionBDD.getInstance().getListeElem("Ingredient"));

    }

    public List<Serveur> getServeurs() {
        return serveurs;
    }

    public Serveur[] getServeursSandwich(){
        int i = 0;
        Serveur[] result = new Serveur[3];
        for(Serveur s : serveurs){
            if(s.getStatus() == StatusServeur.SANDWICH){
                result[i++] = s;
            }
            if(i == 3){
                return result;
            }
        }
        return result;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public Map<ListeCommandeServeur, List<CommandeContainerPanel>> getListesCommandes() {
        return listesCommandes;
    }

    public String[] getIngredientFromType(String type){
        return ingredients.get(type).toArray(new String[ingredients.get(type).size()]);
    }

    public void addObserver(ManagerObserver observer){
        observers.add(observer);
    }

    public void addCommande(Commande c, int col){
        commandes.add(c);
        new Thread(()->{
            fireEvent(new ManagerEvent(ManagerEventType.AJOUT_COMMANDE, c, col));
            ConnexionBDD.getInstance().insertCommande(c);
        }).start();
    }

    public void updateStatusCommande(Commande c, StatusCommande s){
        if(commandes.contains(c)){
            c.setStatus(s);
            fireEvent(new ManagerEvent(ManagerEventType.COMMANDE_STATUS_CHANGE, c));
        }
    }

    public void updateServeurs(){
        fireEvent(new ManagerEvent(ManagerEventType.SERVEUR_UPDATE));
    }

    private void fireEvent(ManagerEvent e){
        observers.forEach(o -> o.handleEvent(e));
    }
}
