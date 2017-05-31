package bde.renderers;

import bde.models.Serveur;
import bde.models.StatusServeur;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thierry on 31/05/2017.
 */
public class ServeurListRenderer extends JPanel implements ListCellRenderer<Serveur>{

    private JLabel text;

    public ServeurListRenderer(){
        text = new JLabel();
        add(text);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Serveur> list, Serveur value, int index, boolean isSelected, boolean cellHasFocus) {
        if(value.getStatus() != StatusServeur.HS){
            text.setText(value.getNom() + " " + getStatusText(value.getStatus()));
        } else {
            text.setText("");
            setSize(0,0);
        }
        switch (value.getStatus()){
            case COMMIS:
                setBackground(Color.RED);
                break;
            case ORDI:
                setBackground(Color.YELLOW);
                break;
            case HS:
                setBackground(Color.WHITE);
                break;
            case SANDWICH:
                setBackground(Color.GREEN);
                break;
        }
        return this;
    }

    private String getStatusText(StatusServeur statusServeur){
        switch (statusServeur){
            case SANDWICH:
                return "(Sandwichs)";
            case COMMIS:
                return "(Commis)";
            case ORDI:
                return "(Ordi)";
            default:
                return "";
        }
    }
}
