package bde;

import bde.models.Commande;

/**
 * Created by Thierry on 11/06/2017.
 */
public class ManagerEvent {

    private ManagerEventType type;
    private Commande commande;

    private int col;

    public ManagerEvent(ManagerEventType type) {
        this.type = type;
    }

    public ManagerEvent(ManagerEventType type, Commande commande) {
        this.type = type;
        this.commande = commande;
    }

    public ManagerEvent(ManagerEventType type, Commande commande, int col) {
        this.type = type;
        this.commande = commande;
        this.col = col;
    }

    public ManagerEventType getType() {
        return type;
    }

    public Commande getCommande() {
        return commande;
    }

    public int getCol() {
        return col;
    }
}
