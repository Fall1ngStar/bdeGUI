package bde;

import java.util.List;

/**
 * Commande class
 * Created by Thierry
 * 01/06/2017
 */
public class Commande {

    private static int nbCommandes = 0;

    private int idCommande;
    private List<String> contenu;
    private StatusCommande status;

    public Commande(List<String> contenu) {
        this.contenu = contenu;
        status = StatusCommande.PREPARATION;
        idCommande = ++nbCommandes;
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
}
