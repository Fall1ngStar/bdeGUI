package bde;

import javax.swing.*;
import java.awt.*;

/**
 * ServeurCellRenderer class
 * Created by Thierry
 * 31/05/2017
 */
public class ServeurCellRenderer extends JPanel implements ListCellRenderer<Serveur> {

    JLabel text;

    public ServeurCellRenderer() {
        text = new JLabel();
        add(text);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Serveur> list, Serveur value, int index, boolean isSelected, boolean cellHasFocus) {
        text.setText(value.toString());
        switch (value.getStatus()){
            case COMMIS:
                setBackground(Color.RED);
                break;
            case ORDI:
                setBackground(Color.YELLOW);
                break;
            case HS:
                setBackground(Color.GRAY);
                break;
            case SANDWICH:
                setBackground(Color.GREEN);
                break;
        }

        return this;
    }
}
