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
import static javafx.application.ConditionalFeature.FXML;
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
public class AdminHomeController implements Initializable {
    @FXML
    private JFXButton btnTransport;

    @FXML
    private Pane mainPane;

    @FXML
    void OnTransportAction(ActionEvent event) throws IOException {
        btnTransport.setStyle("-fx-background-color: #d388cc;");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Transport.fxml"));
        mainPane.getChildren().setAll(pane);

    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
