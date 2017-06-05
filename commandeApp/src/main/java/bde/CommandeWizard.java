package bde;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Created by Thierry on 01/06/2017.
 */
public class CommandeWizard extends JDialog {

    private JList<JCheckBox> listeIngredients, listeSauces, listeBoissons, listeDesserts;
    private String[] sandwichs = new String[]{
            "Sandwich", "Panini", "Wrap", "Assiete garnie", "Hot Dog"
    };
    private String[] ingredients = new String[]{
            "Poulet", "Maïs", "Brie", "Tomate", "Salade", "Cornichons", "Saucisson", "Fromage"
    };
    private String[] sauces = new String[]{
            "Curry", "Ketchup", "Mayonnaise", "Poivrons Oignons", "Algérienne", "Hamburger"
    };
    private String[] boissons = new String[]{
            "7up", "Coca", "Oasis", "Schwepps", "Grenadine", "Ice Tea"
    };
    private String[] desserts = new String[]{
            "Crêpe nut", "Beignet pomme","Beignet chocolat", "Beignet framboise", "Donut", "Snikers", "KitKat", "Lion", "Muffin", "Panini nut","Kinder bueno"
    };
    private JTextArea commande;
    private JButton push, finalise;
    private JPanel panel, ingredientsContainer, otherContainer;
    private JLabel prixLabel;

    private ButtonGroup typeGroup;
    private JRadioButton[] types;
    private JPanel typesContainer;

    public CommandeWizard() {
        initComponent();
        build();
    }

    private void initComponent() {
        listeIngredients = new IngredientsList(new IngredientListModel(ingredients));
        listeSauces = new IngredientsList(new IngredientListModel(sauces));
        listeDesserts = new IngredientsList(new IngredientListModel(desserts));
        listeBoissons = new IngredientsList(new IngredientListModel(boissons));
        panel = new JPanel();
        ingredientsContainer = new JPanel();
        otherContainer = new JPanel();
        commande = new JTextArea();
        push = new JButton("Ajouter à la commande");
        finalise = new JButton("Finaliser la commande");
        prixLabel = new JLabel("0,00 €");

        typeGroup = new ButtonGroup();
        types = new JRadioButton[sandwichs.length];
        typesContainer = new JPanel();
    }

    private void build() {
        typesContainer.setLayout(new BoxLayout(typesContainer,BoxLayout.Y_AXIS));
        for(int i = 0; i < sandwichs.length; i++){
            types[i] = new JRadioButton(sandwichs[i]);
            typeGroup.add(types[i]);
            typesContainer.add(types[i]);
        }

        ingredientsContainer.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.insets = new Insets(5, 5, 5, 5);
        c.gridy = 1;
        ingredientsContainer.add(typesContainer, c);
        ingredientsContainer.add(listeIngredients, c);
        ingredientsContainer.add(listeSauces, c);
        ingredientsContainer.add(listeBoissons, c);
        ingredientsContainer.add(listeDesserts, c);
        c.gridy = 0;
        ingredientsContainer.add(new JLabel("Type"), c);
        ingredientsContainer.add(new JLabel("Ingrédients"), c);
        ingredientsContainer.add(new JLabel("Sauces"), c);
        ingredientsContainer.add(new JLabel("Boissons"), c);
        ingredientsContainer.add(new JLabel("Desserts"), c);

        commande.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5,5,5,5)
        ));
        commande.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(commande);

        GroupLayout layout = new GroupLayout(otherContainer);
        otherContainer.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(scrollPane)
                        .addGap(20)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(push)
                                .addComponent(prixLabel)
                                .addComponent(finalise)
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addComponent(scrollPane)
                        .addGroup(
                                layout.createSequentialGroup()
                                        .addComponent(push)
                                        .addGap(10)
                                        .addComponent(prixLabel)
                                        .addGap(10)
                                        .addComponent(finalise)
                        )
        );
        otherContainer.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel.setLayout(new GridLayout(2,1));
        panel.add(ingredientsContainer);
        panel.add(otherContainer);

        push.addActionListener(e -> {
            String type = getSelectedButtonText(typeGroup);
            commande.append(type == null ? "" : type + "\n");

            ((IngredientListModel) listeIngredients.getModel()).getSelectedItems().forEach(s -> commande.append(" - " + s + "\n"));
            ((IngredientListModel) listeIngredients.getModel()).clearSelected();
            ((IngredientListModel) listeSauces.getModel()).getSelectedItems().forEach(s -> commande.append(" - " + s + "\n"));
            ((IngredientListModel) listeSauces.getModel()).clearSelected();
            ((IngredientListModel) listeBoissons.getModel()).getSelectedItems().forEach(s -> commande.append(s + "\n"));
            ((IngredientListModel) listeBoissons.getModel()).clearSelected();
            ((IngredientListModel) listeDesserts.getModel()).getSelectedItems().forEach(s -> commande.append(s + "\n"));
            ((IngredientListModel) listeDesserts.getModel()).clearSelected();
            listeIngredients.repaint();
            listeSauces.repaint();
            listeBoissons.repaint();
            listeDesserts.repaint();
        });

        finalise.addActionListener(e-> dispose());
        setContentPane(panel);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Création de commande");
        setVisible(true);
    }

    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                buttonGroup.clearSelection();
                return button.getText();
            }
        }

        return null;
    }

    public Commande getCommande(){
        java.util.List<String> contenu = new ArrayList<>();
        String[] contenuArray = commande.getText().split("\n");
        for(String s : contenuArray){
            contenu.add(s);
        }
        Commande c = new Commande(contenu, 0);
        JOptionPane.showMessageDialog(this, "Commande n°" +  c.getIdCommande());
        return c;
    }

    public static void main(String[] args) {
        new CommandeWizard();
    }

}
