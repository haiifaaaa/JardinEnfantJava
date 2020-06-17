/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

import util.DataSource;

/**
 *
 * @author Haifa
 */
public class UserService {

    Connection cnx = DataSource.getInstance().getCnx();

    public User login(String usernamee, String password) {
         User user = null;
        System.out.println(password);
        try {
            String requete = "SELECT * FROM fos_user where username=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, usernamee);
            ResultSet rs = pst.executeQuery();
            rs.next();

            String hashed = "$2a" + rs.getString("password").substring(3);

            if (BCrypt.checkpw(password, hashed)) {
                System.out.println("It matches");
                
                String username = rs.getString("username");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");

                user = new User();
                user.setUsername(username);
                user.setNom(nom);

            } else {
                System.out.println("It does not match");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
