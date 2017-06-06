package bde.actions;

import bde.panels.ServeurStatusComponent;
import bde.models.StatusServeur;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * SetStatusAction class
 * <p>
 * Action permettant de changer le status d'un serveur
 */
public class SetStatusAction extends AbstractAction {

    private StatusServeur toDefine;
    private ServeurStatusComponent parent;

    /**
     * @param name     Le texte qui sera affiché par le radio bouton
     * @param toDefine Le status à définir par le radio bouton pour le serveur
     * @param parent   Le composaant parent contenant le serveur et les différents radios
     */
    public SetStatusAction(String name, StatusServeur toDefine, ServeurStatusComponent parent) {
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
