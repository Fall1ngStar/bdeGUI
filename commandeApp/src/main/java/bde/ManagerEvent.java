package bde;

/**
 * Created by Thierry on 11/06/2017.
 */
public class ManagerEvent {

    private ManagerEventType type;

    public ManagerEvent(ManagerEventType type) {
        this.type = type;
    }

    public ManagerEventType getType() {
        return type;
    }
}
