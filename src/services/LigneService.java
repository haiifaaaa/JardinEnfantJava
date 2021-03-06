/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Ligne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DataSource;

/**
 *
 * @author yosra
 */
public class LigneService implements IService<Ligne>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Ligne l) {
        try {
            String requete = "INSERT INTO ligne (bus_id,pointDepart,pointArrive,dateDepart,nom,placesDisponibles) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
           pst.setInt(1,(l.getBus_id()));
            pst.setString(2, l.getPointDepart());
            pst.setString(3, l.getPointArrive());
            pst.setDate(4, new java.sql.Date(l.getDateDepart().getTime()));
            pst.setString(5, l.getNom());
            pst.setInt(6, l.getPlacesDisponibles());
            pst.executeUpdate();
            System.out.println("Ligne ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Ligne l) {
       try {
            String requete = "DELETE FROM ligne WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, l.getId());
            pst.executeUpdate();
            System.out.println("Ligne supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Ligne l) {
        try {
            String requete;
            requete = "UPDATE ligne SET bus_id = ?, pointDepart=?,pointArrive=?, dateDepart=?, nom=? ,placesDisponibles=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, l.getBus_id());
            pst.setString(2, l.getPointDepart());
            pst.setString(3, l.getPointArrive());
            pst.setDate(4,new java.sql.Date(l.getDateDepart().getTime()));
            pst.setString(5, l.getNom());
            pst.setInt(6, l.getPlacesDisponibles());
            pst.setInt(7, l.getId());
         
            
            pst.executeUpdate();
            System.out.println("Ligne modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Ligne> afficher() {
      List<Ligne> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM ligne";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                list.add(new Ligne(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6),rs.getInt(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
         
    
    }
             public void reserver(int idLigne,int idUser) {
        try {
            String requete = "INSERT INTO reservation_transport (idLigne,idUser) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
           pst.setInt(1,(idLigne));
            pst.setInt(2, idUser);
            pst.executeUpdate();
            System.out.println("reservation_transport ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
             
              public void decrementerNombreDeplace(int id) {
        try {
            String requete;
            requete = "UPDATE ligne SET placesDisponibles = placesDisponibles-1 WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
         
            
            pst.executeUpdate();
            System.out.println("nombre de places decrementé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  
    }

