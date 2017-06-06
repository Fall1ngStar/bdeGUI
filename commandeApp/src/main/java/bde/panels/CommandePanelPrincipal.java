package bde.panels;

import javax.swing.*;
import java.awt.*;

/**
 * CommandePanelPrincipal class
 * <p>
 * Panel principal de l'application pour celui à l'ordi
 * Il regroupe les 3 panels de gestion des serveurs, des commandes et des stocks
 */
public class CommandePanelPrincipal extends JPanel {

    private JTabbedPane tabbedPane;

    private ServeurPanel serveurPanel;
    private GestionCommandePanel gestionCommandePanel;
    private StockPanel stockPanel;

    public CommandePanelPrincipal() {
        initComponents();
        build();
    }

    /**
     * Initialise les composants du panel
     */
    private void initComponents() {
        tabbedPane = new JTabbedPane();
        serveurPanel = new ServeurPanel();
        gestionCommandePanel = new GestionCommandePanel();
        stockPanel = new StockPanel();
    }

    /**
     * Organise les éléments sur le panel
     */
    private void build() {
        tabbedPane.add("Serveurs", serveurPanel);
        tabbedPane.add("Commandes", gestionCommandePanel);
        tabbedPane.add("Stocks", stockPanel);

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.setSelectedComponent(gestionCommandePanel);
    }
}
