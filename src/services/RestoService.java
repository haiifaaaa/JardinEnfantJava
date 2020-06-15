/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entities.Resto;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DataSource;




/**
 *
 * @author Haifa
 */
public class RestoService {
    Connection cnx = DataSource.getInstance().getCnx();
    

    public void ajouter(Resto c) {

        try {
            String requete = "INSERT INTO resto (nom,description,adress,nbplace) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);

            //pst.setInt(1, c.getId());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getDescription());
            pst.setString(4, c.getAdresse());
            pst.setInt(5, c.getNbrdeplace());
            pst.executeUpdate();
            System.out.println("Restaurant ajouté !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    


 
    public void supprimer(Resto c) {
        try {
            String requete = "DELETE FROM resto WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Restaurant supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

   
    public void modifier(Resto c) {
        try {
            String requete;
            requete = "UPDATE resto SET nom=?,description=? ,adress=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getDescription());
            pst.setString(4, c.getAdresse());
            pst.setInt(5, c.getNbrdeplace());
            pst.executeUpdate();
            System.out.println("restaurant modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public List<Resto> afficher() {
        List<Resto> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM resto";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                list.add(new Resto( rs.getString(1),rs.getString(2), rs.getString(3), rs.getInt(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;

    }
}