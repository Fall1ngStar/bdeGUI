package bde.panels;

import javax.swing.*;
import java.awt.*;

/**
 * CommandePanelPrincipal class
 * Created by Thierry
 * 31/05/2017
 */
public class CommandePanelPrincipal extends JPanel {

    private JTabbedPane tabbedPane;

    private ServeurPanel serveurPanel;
    private GestionCommandePanel gestionCommandePanel;
    private StockPanel stockPanel;

    public CommandePanelPrincipal(){
        initComponents();
        build();
    }

    private void initComponents(){
        tabbedPane = new JTabbedPane();
        serveurPanel = new ServeurPanel();
        gestionCommandePanel = new GestionCommandePanel();
        stockPanel = new StockPanel();
    }

    private void build(){
        tabbedPane.add("Serveurs",serveurPanel);
        tabbedPane.add("Commandes",gestionCommandePanel);
        tabbedPane.add("Stocks",stockPanel);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.setSelectedComponent(gestionCommandePanel);
    }
}
