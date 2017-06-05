package bde;

import javax.swing.*;
import java.util.List;

/**
 * Created by Thierry on 01/06/2017.
 */
public class CommandeWizard extends JDialog {

    JList<JCheckBox> listeIngredients;
    String[] ingredients = new String[]{
            "Ingrédient x", "Ingrédient y", "Ingrédient x", "Ingrédient y", "Ingrédient x", "Ingrédient y"
    };
    JTextArea commande;
    JButton push;
    JPanel panel;

    public CommandeWizard() {
        initComponent();
        build();
    }

    private void initComponent() {
        listeIngredients = new IngredientsList(new IngredientListModel(ingredients));
        panel = new JPanel();
        commande = new JTextArea();
        push = new JButton("Ajouter à la commande");
    }

    private void build() {
        panel.add(listeIngredients);
        panel.add(push);
        panel.add(commande);

        push.addActionListener(e -> {
            List<String> current = ((IngredientListModel) listeIngredients.getModel()).getSelectedItems();
            for (String s : current) {
                commande.append(s + "\n");
            }
            ((IngredientListModel) listeIngredients.getModel()).clearSelected();
        });
        setContentPane(panel);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CommandeWizard();
    }

}
