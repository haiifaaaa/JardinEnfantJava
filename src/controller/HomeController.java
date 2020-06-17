
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    
    @FXML
    private JFXButton btnRestoHome;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private HBox hblogout;
    @FXML
    private Label lnUsername;
    @FXML
    private JFXButton btnlogout;
      
    public static User user;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(user==null){
            btnLogin.setVisible(true);
            hblogout.setVisible(false);
        }else{
            btnLogin.setVisible(false);
            hblogout.setVisible(true);
            lnUsername.setText(user.getUsername());
        }
        
        
    }    
    
    @FXML
    void listLigne(ActionEvent event) throws IOException {
        btnTransportHome.setStyle("-fx-background-color: #d388cc;");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/TransportFront.fxml"));
        mainPaneHome.getChildren().setAll(pane);
    }

    @FXML
    private void pageresto(ActionEvent event) throws IOException {
        
        btnRestoHome.setStyle("-fx-background-color: #d388cc;");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/Resto.fxml"));
        mainPaneHome.getChildren().setAll(pane);
        //mainPaneHome.getChildren().setAll(pane);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        mainPaneHome.getChildren().setAll(pane);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        user=null;
    }
   
    
    
    
}
