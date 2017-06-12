package bde;

import bde.models.Commande;

/**
 * Created by Thierry on 11/06/2017.
 */
public class ManagerEvent {

    private ManagerEventType type;
    private Commande commande;

    public ManagerEvent(ManagerEventType type) {
        this.type = type;
    }

    public ManagerEvent(ManagerEventType type, Commande commande) {
        this.type = type;
        this.commande = commande;
    }

    public ManagerEventType getType() {
        return type;
    }

    public Commande getCommande() {
        return commande;
    }
}
