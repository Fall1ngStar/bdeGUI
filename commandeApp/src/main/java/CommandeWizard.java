import javax.swing.*;

/**
 * Created by Thierry on 01/06/2017.
 */
public class CommandeWizard extends JDialog{

    private JButton suivant, precedent, fin;
    private JPanel type, ingredients;

    public CommandeWizard() {
        build();
    }

    private void initComponent(){

    }

    private void build(){
        setSize(1280,720);
        setVisible(true);

        JButton process = new JButton("");
        add(process);
    }

    public static void main(String[] args){
        JOptionPane.showInputDialog("test");
    }

}
