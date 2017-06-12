package bde.panels;

import bde.Manager;
import bde.models.Commande;
import bde.models.StatusCommande;

import javax.swing.*;
import java.awt.*;

/**
 * CommandeContainerPanel class
 * <p>
 * Conteneur permettant l'affichage d'une commande
 * Utilisé dans le {@link GestionCommandePanel GestionCommandePanel}
 */
public class CommandeContainerPanel extends JPanel {

    // La commande liée à ce panel
    private Commande commande;

    // Bouttons permettant de changer l'état de la commande
    private JButton finPrepa, delivre;
    // Labels pour afficher le status de la commande et son numéro
    private JLabel noCommande, status;
    // TextArea pour afficher le contenu de la commande
    private JTextArea contenuCommande;

    /**
     * @param commande La commande liée à ce panel
     */
    public CommandeContainerPanel(Commande commande) {
        this.commande = commande;

        initComponent();
        build();
        createInterractions();
    }

    /**
     * Initialise les différent composant du conteneur
     */
    private void initComponent() {
        finPrepa = new JButton("Commande prête");
        delivre = new JButton("Commande récupérée");
        noCommande = new JLabel("Commande n° " + commande.getIdCommande() + " : ");
        status = new JLabel(commande.getStatus().getNom());
        contenuCommande = new JTextArea();
    }

    /**
     * Organise les éléments dans le conteneur
     */
    private void build() {
        for (String s : commande.getContenu()) {
            contenuCommande.append(s + "\n");
        }
        contenuCommande.setText(contenuCommande.getText().substring(0, contenuCommande.getText().length() - 1));
        contenuCommande.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        contenuCommande.setFont(contenuCommande.getFont().deriveFont(20f));
        contenuCommande.setEditable(false);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(noCommande)
                                        .addComponent(status)
                                )
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(finPrepa)
                                        .addComponent(delivre)
                                )
                                .addComponent(contenuCommande)
                        )
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(noCommande)
                                .addComponent(status)
                        )
                        .addGroup(layout.createParallelGroup()
                                .addComponent(finPrepa)
                                .addComponent(delivre)
                        )
                        .addComponent(contenuCommande)
        );

        setBackground(Color.PINK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    }

    /**
     * Définit les interractions des bouttons
     */
    private void createInterractions() {
        finPrepa.addActionListener(e -> {
            Manager.getInstance().updateStatusCommande(commande, StatusCommande.PRETE);
            updateStatus();
        });
        delivre.addActionListener(e -> {
            Manager.getInstance().updateStatusCommande(commande, StatusCommande.REMISE);
            delivre.setEnabled(false);
            updateStatus();
        });
    }

    /**
     * Met à jour le status de la commande
     */
    public void updateStatus() {
        status.setText(commande.getStatus().getNom());
        if (commande.getStatus() == StatusCommande.PRETE) {
            finPrepa.setEnabled(false);
            contenuCommande.setVisible(false);
        }
    }

    public void hideButtons(){
        finPrepa.setVisible(false);
        delivre.setVisible(false);
    }

    @Override
    public Dimension getMaximumSize() {
        return super.getPreferredSize();
    }
}
