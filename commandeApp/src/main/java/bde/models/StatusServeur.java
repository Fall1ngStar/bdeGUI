package bde.models;

/**
 * StatusServeur class
 * <p>
 * Enumération des différents postes d'un serveur
 */
public enum StatusServeur {
    ORDI(1), SANDWICH(3), COMMIS(2), HS(0);

    int priorite;

    StatusServeur(int i) {
        priorite = i;
    }

    public int getPriorite() {
        return priorite;
    }
}
