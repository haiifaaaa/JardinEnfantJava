/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entities.Ligne;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author asus_pc
 */
public class SingleLigneController implements Initializable {

    @FXML
    private Label PointDepartLabel;
    @FXML
    private Label pointArriveLabel;
    @FXML
    private Label DateLabel;
    @FXML
    private Label NomLabel;
    @FXML
    private Label NombrePlaceLabel;
    @FXML
    private JFXButton reserverBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void getInfo(Ligne l) {
        PointDepartLabel.setText(l.getPointDepart());
        pointArriveLabel.setText(l.getPointArrive());
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String date = df.format(l.getDateDepart());

        DateLabel.setText(date);
        NomLabel.setText(l.getNom());
       NombrePlaceLabel.setText(Integer.toString(l.getPlacesDisponibles()));
    }

    JFXButton getButton() {
        return reserverBtn;
    }
   

}
