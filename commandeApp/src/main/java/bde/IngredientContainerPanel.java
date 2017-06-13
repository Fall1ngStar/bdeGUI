package bde;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thierry on 13/06/2017.
 */
public class IngredientContainerPanel extends JPanel {

    private String ingredient;
    private int quantite;

    private JLabel nomIngredient, labelQuantite;
    private JButton plus, moins;


    public IngredientContainerPanel(String ingredient){
        this.ingredient = ingredient;
        quantite = ConnexionBDD.getInstance().getQuantite(ingredient);
        initComponents();
        build();
        createInterractions();
    }

    private void initComponents(){
        nomIngredient = new JLabel(ingredient){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(150,(int)super.getPreferredSize().getHeight());
            }
        };
        labelQuantite = new JLabel("" + quantite){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(25,(int)super.getPreferredSize().getHeight());
            }
        };
        labelQuantite.setAlignmentX(CENTER_ALIGNMENT);
        plus = new JButton("+");
        moins = new JButton("-");
    }

    private void build(){
        add(nomIngredient);
        add(plus);
        add(labelQuantite);
        add(moins);
    }

    private void createInterractions(){
        plus.addActionListener(e -> {
            quantite++;
            labelQuantite.setText("" + quantite);
            new Thread(()-> ConnexionBDD.getInstance().setQuantite(ingredient, ConnexionBDD.getInstance().getQuantite(ingredient)+1)).start();
        });
        moins.addActionListener(e -> {
            quantite--;
            labelQuantite.setText("" + quantite);
            new Thread(()-> ConnexionBDD.getInstance().setQuantite(ingredient, ConnexionBDD.getInstance().getQuantite(ingredient)-1)).start();
        });
    }

    @Override
    public Dimension getMaximumSize() {
        return super.getPreferredSize();
    }
}
