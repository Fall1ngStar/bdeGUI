package bde;

/**
 * StatusCommande class
 * Created by Thierry
 * 01/06/2017
 */
public enum StatusCommande {
    PREPARATION("En préparation"),PRETE("Prête"),REMISE("Remise");

    String nom;

    StatusCommande(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
