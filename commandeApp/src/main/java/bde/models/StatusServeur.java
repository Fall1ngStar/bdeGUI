package bde.models;

/**
 * StatusServeur class
 * Created by Thierry
 * 31/05/2017
 */
public enum StatusServeur {
    ORDI(1),SANDWICH(3),COMMIS(2),HS(0);

    int priorite;

    StatusServeur(int i){
        priorite = i;
    }

    public int getPriorite() {
        return priorite;
    }
}
