package bde;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thierry on 31/05/2017.
 */
public class ServeurListModel extends AbstractListModel<Serveur> implements ListModel<Serveur> {

    private List<Serveur> toutServeurs;
    private List<Serveur> serveursActifs;

    public ServeurListModel(Serveur[] serveurs){
        toutServeurs = new ArrayList<>();
        serveursActifs = new ArrayList<>();
        for(Serveur s : serveurs){
            toutServeurs.add(s);
            if(s.getStatus() != StatusServeur.HS){
                serveursActifs.add(s);
            }
        }
    }

    @Override
    public int getSize() {
        return serveursActifs.size();
    }

    @Override
    public Serveur getElementAt(int index) {
        return serveursActifs.get(index);
    }

    public void updateServeurList(){
        serveursActifs.clear();
        for(Serveur  s : toutServeurs){
            if(s.getStatus() != StatusServeur.HS){
                serveursActifs.add(s);
            }
        }
    }
}
