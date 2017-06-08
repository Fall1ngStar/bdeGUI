package bde;

import javax.swing.*;
import java.sql.*;

/**
 * Created by Thierry on 08/06/2017.
 */
public class ConnexionBDD {

    public static void main(String[] a){
        try{
            Connection c = DriverManager.getConnection("jdbc:sqlite:bde.bdd");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' ");
            int nbTables = 0;
            while (rs.next()){
                nbTables++;
            }
            if (nbTables == 0) {
                initDatabase(stmt);
            }
            JOptionPane.showMessageDialog(null,"Connected to database");
            System.out.println("Connected to database");


            stmt.close();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private static void initDatabase(Statement stmt) throws SQLException{
        stmt.execute("CREATE TABLE SERVEURS(ID_SERVEUR INT PRIMARY KEY NOT NULL, NOM_SERVEUR TEXT NOT NULL, STATUS_SERVEUR TEXT NOT NULL)");
        stmt.execute("CREATE TABLE COMMANDE(ID_COMMANDE INT PRIMARY KEY NOT NULL, STATUS_COMMANDE TEXT NOT NULL)");
        //stmt.execute("CREATE TABLE ");
        //stmt.execute("");
    }
}
