package bde;

import bde.panels.CommandeContainerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CommandeContainerContextMenu class
 * Created by Thierry
 * 07/06/2017
 */
public class CommandeContainerContextMenu extends JPopupMenu {
    JMenuItem delete;
    JMenu moveTo;
    int i;
    public  CommandeContainerContextMenu(ListeCommandeServeur parent, CommandeContainerPanel toMove,ListeCommandeServeur[] serveurs){
        delete = new JMenuItem("Supprimer la commande");
        moveTo = new JMenu("Définir le serveur");
        for(i = 0; i < serveurs.length; i++){
            JMenuItem item = new JMenuItem("Serveur " + (1+i));
            item.addActionListener(new MoveAction(parent, serveurs[i], toMove));
            moveTo.add(item);
        }
        add(delete);
        add(moveTo);
        delete.addActionListener(e->{
            if (JOptionPane.showConfirmDialog(parent,"Voulez-vous vraiment supprimer cette commande ?","Alerte",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                parent.remove(toMove);
                parent.repaint();
                parent.revalidate();
            }
        });
    }

    private class MoveAction implements ActionListener{

        ListeCommandeServeur from, to;
        CommandeContainerPanel toMove;

        public MoveAction(ListeCommandeServeur from, ListeCommandeServeur to, CommandeContainerPanel toMove) {
            this.from = from;
            this.to = to;
            this.toMove = toMove;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            from.remove(toMove);
            to.add(toMove);
            from.repaint();
            from.revalidate();
            to. repaint();
            to.revalidate();
        }
    }
}
