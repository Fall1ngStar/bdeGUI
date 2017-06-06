package bde.models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IngredientListModel class
 * <p>
 * Modèle pour une liste d'ingrédients sélectionnables via checkbox
 */
public class IngredientListModel extends DefaultListModel<JCheckBox> implements ListModel<JCheckBox> {

    private List<JCheckBox> values;

    /**
     * @param names Les différents ingrédients de base de la liste
     */
    public IngredientListModel(String[] names) {
        values = new ArrayList<>();
        for (String s : names) {
            values.add(new JCheckBox(s));
        }
    }

    /**
     * @return Le nombre d'élements de la liste
     */
    @Override
    public int getSize() {
        return values.size();
    }

    /**
     * @param index l'index de la valeur voulue
     * @return la valeur à l'index
     */
    @Override
    public JCheckBox getElementAt(int index) {
        return values.get(index);
    }


    /**
     * @return Une liste des ingrédients sélectionnés
     */
    public List<String> getSelectedItems() {
        List<String> result = new ArrayList<>();
        for (JCheckBox c : values) {
            if (c.isSelected()) {
                result.add(c.getText());
            }
        }
        return result;
    }

    /**
     * Déselectionne tout les éléments
     */
    public void clearSelected() {
        values.forEach(e -> e.setSelected(false));
    }

}
