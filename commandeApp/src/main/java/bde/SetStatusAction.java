package bde;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * SetStatusAction class
 * Created by Thierry
 * 31/05/2017
 */
public class SetStatusAction extends AbstractAction {

    private StatusServeur toDefine;
    private ServeurPanel parent;

    public SetStatusAction(String name, StatusServeur toDefine, ServeurPanel parent){
        super(name);
        this.toDefine = toDefine;
        this.parent = parent;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ((Serveur)parent.getComboServeurs().getSelectedItem()).setStatus(toDefine);
        ((ServeurListModel)parent.getListeServeursActifs().getModel()).updateServeurList();
        parent.getListeServeursActifs().updateUI();
    }
}
