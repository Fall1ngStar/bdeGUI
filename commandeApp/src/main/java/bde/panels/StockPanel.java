package bde.panels;

import bde.ConnexionBDD;
import bde.IngredientContainerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * StockPanel class
 *
 * Panel pour l'affichage et la mofidication des stocks
 */
public class StockPanel extends JPanel {

    public StockPanel(){
        setLayout(new BorderLayout());
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));

        for(String s : ConnexionBDD.getInstance().getListeAllElems()){
            innerPanel.add(new IngredientContainerPanel(s));
        }

        JScrollPane scrollPane = new JScrollPane(innerPanel);
        add(scrollPane, BorderLayout.CENTER);
    }
}
