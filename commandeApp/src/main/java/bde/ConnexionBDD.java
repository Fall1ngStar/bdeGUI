package bde;

import bde.models.Commande;
import bde.models.Serveur;

import javax.swing.*;
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
            if (nbTables == 0) initDatabase();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erreur : Impossible de se connecter à la base de donnée");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void initDatabase() throws SQLException{
        stmt.execute("CREATE TABLE SERVEURS(ID_SERVEUR INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NOM_SERVEUR TEXT NOT NULL)");
        stmt.execute("CREATE TABLE COMMANDE(ID_COMMANDE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NUM_COMMANDE INTEGER NOT NULL, STATUS_COMMANDE TEXT NOT NULL, DATE TEXT NOT NULL)");
        stmt.execute("CREATE TABLE ING_CMD (ID_COMMANDE INTEGER, ID_INGREDIENT INTEGER, NUM_ORDRE INTEGER)");
        stmt.execute("CREATE TABLE INGREDIENTS (ID_INGREDIENT INTEGER PRIMARY KEY , LIBELLE TEXT, STOCK INTEGER, TYPE_INGREDIENT TEXT)");
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


    public static void main(String[] arr){
        ConnexionBDD.getInstance().insertCommande(new Commande(null, 10));
    }
}
