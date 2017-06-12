package bde.panels;

import bde.Manager;
import bde.models.Commande;
import bde.models.IngredientListModel;
import bde.models.IngredientsList;

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

    // Tableaux contenant les noms des différents types
    private String[] sandwichs = new String[]{
            "Sandwich", "Panini", "Wrap", "Assiete garnie", "Hot Dog"
    };

    // TextArea affichant le contenu de la commande
    private JTextArea affichageContenuCommande;

    // Bouttons pour ajouter les éléments sélectionnés à la commande et pour finaliser la commande
    private JButton push, finalise;
    // Panels permettant l'organisation des différents éléments affichés
    private JPanel panel, ingredientsContainer, otherContainer;
    // Label affichant le prix
    // TODO: Calculer le prix
    private JLabel prixLabel;
    private double prix;

    // Objets permettant la sélection du type de plat principal
    private ButtonGroup typeGroup;
    private JRadioButton[] types;
    private JPanel typesContainer;

    private boolean prete;

    private java.util.List<String[]> contenuCommande;

    public CommandeWizard() {
        initComponent();
        build();
        createInterractions();
    }

    /**
     * Initialise les différents composants
     */
    private void initComponent() {
        listeIngredients = new IngredientsList(new IngredientListModel(Manager.getInstance().getIngredientFromType("Ingredient")));
        listeSauces = new IngredientsList(new IngredientListModel(Manager.getInstance().getIngredientFromType("Sauce")));
        listeDesserts = new IngredientsList(new IngredientListModel(Manager.getInstance().getIngredientFromType("Dessert")));
        listeBoissons = new IngredientsList(new IngredientListModel(Manager.getInstance().getIngredientFromType("Boisson")));
        panel = new JPanel();
        ingredientsContainer = new JPanel();
        otherContainer = new JPanel();
        affichageContenuCommande = new JTextArea();
        push = new JButton("Ajouter à la commande");
        finalise = new JButton("Finaliser la commande");
        prixLabel = new JLabel("0,00 €");
        {
            typeGroup = new ButtonGroup();
            ;
        }
        types = new JRadioButton[sandwichs.length];
        typesContainer = new JPanel();

        prete = false;

        contenuCommande = new ArrayList<>();
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
        affichageContenuCommande.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        affichageContenuCommande.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(affichageContenuCommande);

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
                                        .addGap(100)
                                        .addComponent(prixLabel)
                                        .addGap(10)
                                        .addComponent(finalise)
                                        .addGap(20)
                        )
        );
        otherContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(2, 1));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(ingredientsContainer);
        panel.add(otherContainer);

        // Affiche et définit les propriétés du JDialog
        setContentPane(panel);
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
            affichageContenuCommande.append(type == null ? "" : type + "\n");
            if (type != null && !type.equals("")) {
                contenuCommande.add(new String[]{"Type", type});
            }

            ((IngredientListModel) listeIngredients.getModel()).getSelectedItems().forEach(s -> {
                contenuCommande.add(new String[]{"Ingredient", s});
                affichageContenuCommande.append(" - " + s + "\n");
            });
            ((IngredientListModel) listeIngredients.getModel()).clearSelected();
            ((IngredientListModel) listeSauces.getModel()).getSelectedItems().forEach(s -> {
                contenuCommande.add(new String[]{"Sauce", s});
                affichageContenuCommande.append(" - " + s + "\n");
            });
            ((IngredientListModel) listeSauces.getModel()).clearSelected();
            ((IngredientListModel) listeBoissons.getModel()).getSelectedItems().forEach(s -> {
                contenuCommande.add(new String[]{"Boisson", s});
                affichageContenuCommande.append(s + "\n");
            });
            ((IngredientListModel) listeBoissons.getModel()).clearSelected();
            ((IngredientListModel) listeDesserts.getModel()).getSelectedItems().forEach(s -> {
                contenuCommande.add(new String[]{"Dessert", s});
                affichageContenuCommande.append(s + "\n");
            });
            ((IngredientListModel) listeDesserts.getModel()).clearSelected();
            listeIngredients.repaint();
            listeSauces.repaint();
            listeBoissons.repaint();
            listeDesserts.repaint();
            updatePrix();
        });

        // Ferme la fenêtre (La commande est récupéré par le GestionCommandPanel
        finalise.addActionListener(e -> {
            prete = true;
            dispose();
        });
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

    private void updatePrix() {
        prix = 0.0;
        int nbPlat = 0;
        int nbSauce = 0;
        int nbIngredient = 0;
        int nbDessert = 0;
        int nbBoisson = 0;

        for (String[] element : contenuCommande) {
            switch (element[0]) {
                case "Type":
                    nbPlat += 1;
                    nbIngredient = 0;
                    nbSauce = 0;
                    switch (element[1]) {
                        case "Sandwich":
                        case "Wrap":
                            prix += 2.0;
                            break;
                        case "Hot Dog":
                            prix += 1.5;
                            break;
                        case "Panini":
                            prix += 2.5;
                            break;
                        default:
                    }
                    break;
                case "Ingredient":
                    nbIngredient++;
                    if (nbIngredient > 3) prix += 0.3;
                    break;
                case "Sauce":
                    nbSauce++;
                    if (nbSauce > 2) prix += 0.3;
                    break;
                case "Boisson":
                    nbBoisson++;
                    prix += 0.5;
                    break;
                case "Dessert":
                    nbDessert++;
                    prix += 0.8;
                    if (element[1].equals("Panini nut")) prix += 0.2;
                    break;
            }
        }
        prix -= Math.min(nbPlat, Math.min(nbBoisson, nbDessert)) * 0.3;
        prixLabel.setText(String.format("%.2f", prix) + " €");

    }

    /**
     * @return La commande créée
     */
    public Commande getCommande() {
        if (affichageContenuCommande.getText().equalsIgnoreCase("") || !prete) return null;
        java.util.List<String> contenu = new ArrayList<>();
        for (String[] elem : contenuCommande) {
            contenu.add(elem[1]);
        }
        Commande c = new Commande(contenu, 0);

        JOptionPane.showMessageDialog(this, "Commande n°" + c.getIdCommande());
        return c;
    }

}
