package bde;

import javax.swing.*;
import java.util.List;

/**
 * Created by Thierry on 01/06/2017.
 */
public class CommandeWizard extends JDialog {

    JList<JCheckBox> listeSandwichs, listeIngredients, listeSauces, listeBoissons, listeDesserts;
    String[] sandwichs = new String[]{
            "Sandwich", "Panini", "Wrap", "Assiete garnie", "Hot Dog"
    };
    String[] ingredients = new String[]{
            "Poulet", "Maïs", "Brie", "Tomate", "Salade", "Cornichons", "Saucisson", "Fromage"
    };
    String[] sauces = new String[]{
            "Curry", "Ketchup", "Mayonnaise", "Poivrons Oignons", "Algérienne", "Hamburger"
    };
    String[] boissons = new String[]{
            "7up", "Coca", "Oasis", "Schwepps", "Grenadine", "Ice Tea"
    };
    String[] desserts = new String[]{
            "Crêpe nut", "Beignet pomme/chocolat/framboise", "Donut", "Snikers", "KitKat", "Lion", "Muffin", "Panini nut"
    };
    JTextArea commande;
    JButton push;
    JPanel panel;

    public CommandeWizard() {
        initComponent();
        build();
    }

    private void initComponent() {
        listeSandwichs = new IngredientsList(new IngredientListModel(sandwichs));
        listeIngredients = new IngredientsList(new IngredientListModel(ingredients));
        listeSauces = new IngredientsList(new IngredientListModel(sauces));
        listeDesserts = new IngredientsList(new IngredientListModel(desserts));
        listeBoissons = new IngredientsList(new IngredientListModel(boissons));
        panel = new JPanel();
        commande = new JTextArea();
        push = new JButton("Ajouter à la commande");
    }

    private void build() {
        panel.add(listeSandwichs);
        panel.add(listeIngredients);
        panel.add(listeSauces);
        panel.add(listeDesserts);
        panel.add(listeBoissons);
        panel.add(push);
        panel.add(commande);

        push.addActionListener(e -> {
            ((IngredientListModel) listeSandwichs.getModel()).getSelectedItems().forEach(s->commande.append(s + "\n"));
            ((IngredientListModel) listeSandwichs.getModel()).clearSelected();
            ((IngredientListModel) listeIngredients.getModel()).getSelectedItems().forEach(s->commande.append(" - " + s + "\n"));
            ((IngredientListModel) listeIngredients.getModel()).clearSelected();
            ((IngredientListModel) listeSauces.getModel()).getSelectedItems().forEach(s->commande.append(" - " + s + "\n"));
            ((IngredientListModel) listeSauces.getModel()).clearSelected();
            ((IngredientListModel) listeBoissons.getModel()).getSelectedItems().forEach(s->commande.append(s + "\n"));
            ((IngredientListModel) listeBoissons.getModel()).clearSelected();
            ((IngredientListModel) listeDesserts.getModel()).getSelectedItems().forEach(s->commande.append(s + "\n"));
            ((IngredientListModel) listeDesserts.getModel()).clearSelected();
            listeIngredients.repaint();
            listeSauces.repaint();
            listeBoissons.repaint();
            listeDesserts.repaint();
            listeSandwichs.repaint();
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
