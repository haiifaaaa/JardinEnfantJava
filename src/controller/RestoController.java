/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import entities.Chauffeur;
import entities.Resto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ChauffeurService;
import services.RestoService;

/**
 * FXML Controller class
 *
 * @author Haifa
 */
public class RestoController implements Initializable {

    @FXML
    private JFXTextField nomTxt;
    @FXML
    private JFXTextField descriptionTxt;
    @FXML
    private JFXTextField AdresseTxt;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextField NombredeplaceTxt;
    @FXML
    private TableView<Resto> tableRestaurant;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> cin;
    @FXML
    private TableColumn<?, ?> Prix;
    @FXML
    private JFXTextField prixTxt;
    @FXML
    private JFXTextField categorieTxt;
    @FXML
    private JFXTextField NbdeplaceTxt;
    @FXML
    private DatePicker datefin;
    @FXML
    private TableView<?> tableResto;
    @FXML
    private TableColumn<?, ?> nomR;
    @FXML
    private TableColumn<?, ?> AdresseR;
    @FXML
    private TableView<?> tablePlat;
    @FXML
    private TableColumn<?, ?> nomP;
    @FXML
    private TableColumn<?, ?> descriptionP;
    @FXML
    private TableColumn<?, ?> catégorieP;
    @FXML
    private TableColumn<?, ?> prixP;
    @FXML
    private TableColumn<?, ?> imageP;
    @FXML
    private DatePicker datedebut;
    @FXML
    private JFXTextField marqueTxt;

    /**
     * Initializes the controller class.
     */
    RestoService ps = new RestoService();
    public ObservableList<Resto> observablelistResto = FXCollections.observableArrayList(
            ps.afficher()
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableRestaurant.setItems(observablelistResto);
    }    

     public void showNotif(){
         Image img = new Image("/uploads/error.png");
                ImageView imgV = new ImageView(img);
                imgV.setFitHeight(50);
                imgV.setFitWidth(50);
                
                  Notifications notif = Notifications.create()
                .title("  erreur")
                .text(" s'il vous plait de remplir tous les champs !")
                .graphic(imgV)
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_CENTER)
                
                          .onAction(s->{
                         // System.out.println("notif clicked");
                          });
                notif.show();
    
     }
     
      boolean validateFieldsResto(){
        if(nomTxt.getText().isEmpty()||descriptionTxt.getText().isEmpty()||AdresseTxt.getText().isEmpty() ||NombredeplaceTxt.getText().isEmpty()){
        showNotif();
        return false;}
        
    return true;
    }
    
    
    @FXML
    private void onAjouterRestoAction(ActionEvent event) {
        
      /*  if(validateFieldsResto()){
        int nbr = Integer.parseInt(NombredeplaceTxt.getText());
        Resto c = new Resto(nomTxt.getText(), descriptionTxt.getText(), AdresseTxt.getText(), NombredeplaceTxt);
        ChauffeurService sc = new ChauffeurService();
        sc.ajouter(c);*/
        /**
         * refreshing the table view *
         */
       /* list.clear();
        list = FXCollections.observableArrayList(
                sc.afficher()
        );
        tableChauffeur.setItems(list);
        tableChauffeurBus.setItems(list); */
        }
    

    @FXML
    private void onSupprimerRestoAction(ActionEvent event) {
    }

    @FXML
    private void onModifierAction(ActionEvent event) {
    }

    @FXML
    private void onModifierPlatAction(ActionEvent event) {
    }

    @FXML
    private void onSupprimerPlatAction(ActionEvent event) {
    }

    @FXML
    private void onAjouterPlatAction(ActionEvent event) {
    }
    
}
