package bde.models;

/**
 * Serveur class
 * <p>
 * Classe représentant un serveur
 */
public class Serveur implements Comparable<Serveur> {
    private String nom;
    private StatusServeur status;

    public Serveur(String nom) {
        this.nom = nom;
        status = StatusServeur.HS;
    }

    public String getNom() {
        return nom;
    }

    public StatusServeur getStatus() {
        return status;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setStatus(StatusServeur status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nom;
    }

    /**
     * Permet de trier les serveurs en fonction de leur status
     *
     * @param o l'autre serveur à comparer à l'instance
     */
    @Override
    public int compareTo(Serveur o) {
        return Integer.compare(o.status.getPriorite(), this.status.getPriorite());
    }
}
