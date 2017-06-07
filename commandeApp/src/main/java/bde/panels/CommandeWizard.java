package bde.panels;

import bde.models.IngredientsList;
import bde.models.Commande;
import bde.models.IngredientListModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * JDialog permettant la création d'une commmande
 */
public class CommandeWizard extends JDialog {

    // JListes contenant les différents éléments pouvant composer une commande
    private JList<JCheckBox> listeIngredients, listeSauces, listeBoissons, listeDesserts;

    // Tableaux contenant les noms des différents éléments des commandes
    // TODO: Charger les noms des composans depuis une base de données (voir en même temps que la gestion des stocks)
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
            "Crêpe nut", "Beignet pomme", "Beignet chocolat", "Beignet framboise", "Donut", "Snikers", "KitKat", "Lion", "Muffin", "Panini nut", "Kinder bueno"
    };

    // TextArea affichant le contenu de la commande
    private JTextArea contenuCommande;

    // Bouttons pour ajouter les éléments sélectionnés à la commande et pour finaliser la commande
    private JButton push, finalise;
    // Panels permettant l'organisation des différents éléments affichés
    private JPanel panel, ingredientsContainer, otherContainer;
    // Label affichant le prix
    // TODO: Calculer le prix
    private JLabel prixLabel;

    // Objets permettant la sélection du type de plat principal
    private ButtonGroup typeGroup;
    private JRadioButton[] types;
    private JPanel typesContainer;

    public CommandeWizard() {
        initComponent();
        build();
        createInterractions();
    }

    /**
     * Initialise les différents composants
     */
    private void initComponent() {
        listeIngredients = new IngredientsList(new IngredientListModel(ingredients));
        listeSauces = new IngredientsList(new IngredientListModel(sauces));
        listeDesserts = new IngredientsList(new IngredientListModel(desserts));
        listeBoissons = new IngredientsList(new IngredientListModel(boissons));
        panel = new JPanel();
        ingredientsContainer = new JPanel();
        otherContainer = new JPanel();
        contenuCommande = new JTextArea();
        push = new JButton("Ajouter à la contenuCommande");
        finalise = new JButton("Finaliser la contenuCommande");
        prixLabel = new JLabel("0,00 €");

        typeGroup = new ButtonGroup();
        types = new JRadioButton[sandwichs.length];
        typesContainer = new JPanel();
    }

    /**
     * Organise les éléments à afficher
     */
    private void build() {
        // Configure le groupe de radio bouttons pour le type de plat principal
        typesContainer.setLayout(new BoxLayout(typesContainer, BoxLayout.Y_AXIS));
        for (int i = 0; i < sandwichs.length; i++) {
            types[i] = new JRadioButton(sandwichs[i]);
            typeGroup.add(types[i]);
            typesContainer.add(types[i]);
        }

        // Affiche les différentes listes d'éléments sélectionnables pour la commande
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


        //Affiche le contenu de la commande et les autres bouttons
        contenuCommande.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        contenuCommande.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contenuCommande);

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
        otherContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(2, 1));
        panel.add(ingredientsContainer);
        panel.add(otherContainer);

        // Affiche et définit les propriétés du JDialog
        setContentPane(panel);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Création de contenuCommande");
        setVisible(true);
    }

    /**
     * Définit les interractions des différents éléments
     */
    private void createInterractions() {
        // Récupère tout les éléments sélectionnnés, les ajoute au contenu de la commande et réinitialise la sélection
        push.addActionListener(e -> {
            String type = getSelectedButtonText(typeGroup);
            contenuCommande.append(type == null ? "" : type + "\n");

            ((IngredientListModel) listeIngredients.getModel()).getSelectedItems().forEach(s -> contenuCommande.append(" - " + s + "\n"));
            ((IngredientListModel) listeIngredients.getModel()).clearSelected();
            ((IngredientListModel) listeSauces.getModel()).getSelectedItems().forEach(s -> contenuCommande.append(" - " + s + "\n"));
            ((IngredientListModel) listeSauces.getModel()).clearSelected();
            ((IngredientListModel) listeBoissons.getModel()).getSelectedItems().forEach(s -> contenuCommande.append(s + "\n"));
            ((IngredientListModel) listeBoissons.getModel()).clearSelected();
            ((IngredientListModel) listeDesserts.getModel()).getSelectedItems().forEach(s -> contenuCommande.append(s + "\n"));
            ((IngredientListModel) listeDesserts.getModel()).clearSelected();
            listeIngredients.repaint();
            listeSauces.repaint();
            listeBoissons.repaint();
            listeDesserts.repaint();
        });

        // Ferme la fenêtre (La commande est récupéré par le GestionCommandPanel
        finalise.addActionListener(e -> dispose());
    }

    /**
     * Récupère le type de plat principal et réinitialise la sélection
     */
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                buttonGroup.clearSelection();
                return button.getText();
            }
        }
        return null;
    }

    /**
     * @return La commande crée
     */
    public Commande getCommande() {
        if(contenuCommande.getText().equalsIgnoreCase("")) return null;
        java.util.List<String> contenu = new ArrayList<>();
        String[] contenuArray = contenuCommande.getText().split("\n");
        for (String s : contenuArray) {
            contenu.add(s);
        }
        Commande c = new Commande(contenu, 0);
        JOptionPane.showMessageDialog(this, "Commande n°" + c.getIdCommande());
        return c;
    }
}
