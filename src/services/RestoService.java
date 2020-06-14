package services;

import entities.Chauffeur;
import entities.Resto;
import util.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestoService implements IService<Resto> {
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Resto resto) {

    }

    @Override
    public void supprimer(Resto resto) {

    }

    @Override
    public void modifier(Resto resto) {

    }

    @Override
    public List<Resto> afficher() {
        List<Resto> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM resto";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                list.add(new Resto(rs.getInt(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4),rs.getInt(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
