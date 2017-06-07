package bde;

import bde.panels.CommandeContainerPanel;

import javax.swing.*;

/**
 * CommandeContainerContextMenu class
 * Created by Thierry
 * 07/06/2017
 */
public class CommandeContainerContextMenu extends JPopupMenu {
    JMenuItem delete;
    public  CommandeContainerContextMenu(JPanel parent, CommandeContainerPanel toDelete){
        delete = new JMenuItem("Supprimer la commande");
        add(delete);
        delete.addActionListener(e->{
            if (JOptionPane.showConfirmDialog(parent,"Voulez-vous vraiment supprimer cette commande ?","Alerte",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                parent.remove(toDelete);
                parent.repaint();
                parent.revalidate();
            }
        });
    }
}
