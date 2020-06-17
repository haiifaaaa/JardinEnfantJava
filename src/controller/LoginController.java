/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Haifa
 */
public class LoginController implements Initializable {

    @FXML
    private Label NomLabel;
    @FXML
    private JFXTextField tfUsername;
    @FXML
    private Label NombrePlaceLabel;
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXButton loginBtn;

    
        UserService us = new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginBtn(ActionEvent event) throws IOException {
        
        HomeController.user =us.login(tfUsername.getText(), tfPassword.getText());
        if(HomeController.user!=null)
        NomLabel.getScene().setRoot(FXMLLoader.load(getClass().getResource("/views/Home.fxml")));
        
    }
    
}
