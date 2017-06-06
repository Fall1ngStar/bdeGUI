package bde.models;

/**
 * StatusCommande class
 * <p>
 * Enumération des status d'une commande
 */
public enum StatusCommande {
    PREPARATION("En préparation"), PRETE("Prête"), REMISE("Remise");

    String nom;

    StatusCommande(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}
