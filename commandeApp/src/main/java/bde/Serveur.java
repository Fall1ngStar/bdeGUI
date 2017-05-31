package bde;

/**
 * Serveur class
 * Created by Thierry
 * 31/05/2017
 */
public class Serveur {
    private String nom;
    private StatusServeur status;

    public Serveur(String nom){
        this.nom = nom;
        status = StatusServeur.HS;
    }

    public String getNom() {
        return nom;
    }

    public StatusServeur getStatus() {
        return status;
    }

    public void setStatus(StatusServeur status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nom;
    }
}
