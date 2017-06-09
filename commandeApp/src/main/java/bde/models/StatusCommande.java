package bde.models;

/**
 * StatusCommande class
 * <p>
 * Enumération des status d'une commande
 */
public enum StatusCommande {
    PREPARATION("En préparation", "PREPARATION"), PRETE("Prête", "PRETE"), REMISE("Remise", "REMISE");

    String nom;
    String dataRepresentation;

    StatusCommande(String nom, String dataRepresentation) {
        this.nom = nom;
        this.dataRepresentation = dataRepresentation;
    }

    public String getNom() {
        return nom;
    }
    public String getDataRepresentation(){
        return dataRepresentation;
    }
}
