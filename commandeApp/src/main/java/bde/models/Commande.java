package bde.models;

import java.util.List;

/**
 * Commande class
 * <p>
 * Classe permettant de représenter une commande
 */
public class Commande {

    private static int nbCommandes = 0;

    // Numéro de la commande
    private int idCommande;

    // Liste des ingrédients, types, boissons, desserts ...
    // TODO: Modifier le type de stockage des ingrédients (à voir en même temps que la gestion des stocks)
    private List<String> contenu;
    private StatusCommande status;
    private double prix;

    /**
     * @param contenu Le contenu de la commande
     * @param prix    le prix
     */
    public Commande(List<String> contenu, double prix) {
        this.contenu = contenu;
        status = StatusCommande.PREPARATION;
        idCommande = ++nbCommandes;
        this.prix = prix;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public List<String> getContenu() {
        return contenu;
    }

    public StatusCommande getStatus() {
        return status;
    }

    public void setStatus(StatusCommande status) {
        this.status = status;
    }


}
