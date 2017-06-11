package bde;

import bde.models.Commande;
import bde.models.Serveur;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Thierry on 08/06/2017.
 */
public class ConnexionBDD {

    private static ConnexionBDD instance = new ConnexionBDD();
    private Connection connection;
    private Statement stmt;

    private ConnexionBDD() {
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:bde.bdd");
            stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' ");
            int nbTables = 0;
            while (rs.next()) nbTables++;
            if (nbTables < 4) initDatabase();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erreur avec la base de données");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void initDatabase() throws SQLException{
        ScriptRunner runner = new ScriptRunner(connection, false, false);
        try {
            runner.runScript(new BufferedReader(new FileReader("init_script.sql")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  ConnexionBDD getInstance(){
        return instance;
    }

    public List<Serveur> getServeurs(){
        List<Serveur> result = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT NOM_SERVEUR FROM SERVEURS");
            while(rs.next()){
                result.add(new Serveur(rs.getString(1)));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addServeur(Serveur s){
        try{
            stmt.executeUpdate("INSERT INTO SERVEURS VALUES ("+s.getNom()+")");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void insertCommande(Commande c){
        try {
            stmt.executeUpdate("INSERT INTO COMMANDE(NUM_COMMANDE, STATUS_COMMANDE, DATE) VALUES ("
                    + c.getIdCommande() + ", '"
                    + c.getStatus().getDataRepresentation() +"', '"
                    + new SimpleDateFormat("dd MM yyyy").format(Calendar.getInstance().getTime())
                    +"' )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getListeElem(String categorie){
        List<String> elems = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT LIBELLE FROM INGREDIENTS WHERE TYPE_INGREDIENT = '" + categorie + "'");
            while(rs.next()){
                elems.add(rs.getString(1));
            }
            return elems;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] arr){
        ConnexionBDD.getInstance().insertCommande(new Commande(null, 10));
    }
}
