package bde;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * IngredientListModel class
 * Created by Thierry
 * 04/06/2017
 */
public class IngredientListModel extends DefaultListModel<JCheckBox> implements ListModel<JCheckBox> {

    private List<JCheckBox> values;

    public IngredientListModel(String[] names){
        values = new ArrayList<>();
        for(String s : names){
            values.add(new JCheckBox(s));
        }
    }

    @Override
    public int getSize() {
        return values.size();
    }

    @Override
    public JCheckBox getElementAt(int index) {
        return values.get(index);
    }

    public List<String> getSelectedItems(){
        List<String> result = new ArrayList<>();
        for(JCheckBox c : values){
            if(c.isSelected()){
                result.add(c.getText());
            }
        }
        return result;
    }

    public void clearSelected(){
        values.forEach(e->e.setSelected(false));
    }

}
