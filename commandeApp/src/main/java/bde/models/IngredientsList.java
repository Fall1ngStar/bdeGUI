package bde.models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * IngredientsList class
 * <p>
 * JList personnalisée permettant d'afficher et de sélectionner des JCheckBox pour les différents ingrédients
 */
public class IngredientsList extends JList<JCheckBox> {

    /**
     * @param boxes Le modèle contenant les JCheckBox à afficher
     */
    public IngredientsList(ListModel<JCheckBox> boxes) {
        super(boxes);
        setCellRenderer(new CellRenderer());
        // Permet de changer l'état d'une JCheckox au click (non géré autrement)
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox box = getModel().getElementAt(index);
                    box.setSelected(!box.isSelected());
                    repaint();
                }
            }
        });
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    /**
     * CellRenderer personnalisé pour l'affichage des checkbox de la liste
     */
    private class CellRenderer extends JCheckBox implements ListCellRenderer<JCheckBox> {
        @Override
        public Component getListCellRendererComponent(JList<? extends JCheckBox> list, JCheckBox value, int index, boolean isSelected, boolean cellHasFocus) {
            setSelected(value.isSelected());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setText(value.getText());
            return this;
        }
    }

}
