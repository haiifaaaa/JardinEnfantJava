/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author asus_pc
 */
public class HomeController implements Initializable {

    @FXML
    private Pane mainPaneHome;

    @FXML
    private JFXButton btnTransportHome;
      
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
    @FXML
    void listLigne(ActionEvent event) throws IOException {
        btnTransportHome.setStyle("-fx-background-color: #d388cc;");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/TransportFront.fxml"));
        mainPaneHome.getChildren().setAll(pane);
    }
   
    
    
}
