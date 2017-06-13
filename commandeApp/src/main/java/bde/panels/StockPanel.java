package bde.panels;

import bde.ConnexionBDD;

import javax.swing.*;
import java.awt.*;

/**
 * StockPanel class
 *
 * Panel pour l'affichage et la mofidication des stocks
 */
public class StockPanel extends JPanel {

    GridLayout grid;
    JLabel ing, qteAffiche;
    JButton moins, plus;
    int qte;
    ConnexionBDD instance = ConnexionBDD.getInstance();
    String texte = String.valueOf(qte);


    public StockPanel(){
        qte = instance.getQuantite(ing.getText());
        grid = new GridLayout(1,4);
        ing = new JLabel("Fromage");
        moins = new JButton("-");
        qteAffiche = new JLabel(texte);
        plus = new JButton("+");

        this.setLayout(grid);

        this.add(ing);
        this.add(moins);
        this.add(qteAffiche);
        this.add(plus);

        plus.addActionListener();
    }
}
