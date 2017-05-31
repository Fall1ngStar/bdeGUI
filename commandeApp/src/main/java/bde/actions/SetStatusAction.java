package bde.actions;

import bde.ServeurStatusComponent;
import bde.models.StatusServeur;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * SetStatusAction class
 * Created by Thierry
 * 31/05/2017
 */
public class SetStatusAction extends AbstractAction {

    private StatusServeur toDefine;
    private ServeurStatusComponent parent;

    public SetStatusAction(String name, StatusServeur toDefine, ServeurStatusComponent parent){
        super(name);
        this.toDefine = toDefine;
        this.parent = parent;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        parent.getServeur().setStatus(toDefine);
        parent.updateColor();
        parent.getParent().updateServeurListOrder();
    }
}
