package bde;

import javax.swing.*;
import java.awt.*;

/**
 * CommandeContainerPanel class
 * Created by Thierry
 * 01/06/2017
 */
public class CommandeContainerPanel extends JPanel {

    private Commande commande;

    private JButton finPrepa, delivre;
    private JLabel noCommande, status;
    private JTextArea contenuCommande;

    public CommandeContainerPanel(Commande commande) {
        this.commande = commande;
        initComponent();
        build();
        createInterractions();
    }

    private void initComponent() {
        finPrepa = new JButton("Commande prête");
        delivre = new JButton("Commande récupérée");
        noCommande = new JLabel("Commande n° " + commande.getIdCommande() + " : ");
        status = new JLabel(commande.getStatus().getNom());
        contenuCommande = new JTextArea();
    }

    private void build() {
        for (String s : commande.getContenu()) {
            contenuCommande.append(s + "\n");
        }
        contenuCommande.setText(contenuCommande.getText().substring(0,contenuCommande.getText().length()-1));
        contenuCommande.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5,5,5,5)
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
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2, true));
    }

    private void createInterractions(){
        finPrepa.addActionListener(e->{
            commande.setStatus(StatusCommande.PRETE);
            finPrepa.setEnabled(false);
            updateStatus();
        });
        delivre.addActionListener(e->{
            commande.setStatus(StatusCommande.REMISE);
            delivre.setEnabled(false);
            updateStatus();
        });
    }

    private void updateStatus(){
        status.setText(commande.getStatus().getNom());
    }
}
