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

import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.RestoService;

import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer;

import java.net.URL;

import static java.nio.file.Files.list;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.util.Collections.list;

import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    private DatePicker datefin;
    @FXML
    private TableColumn<Resto, String> nomR;
    @FXML
    private TableColumn<Resto, String> AdresseR;
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
    private TableColumn<Resto, Integer> idResto;
    @FXML
    private TableColumn<Resto, String> nomResto;
    @FXML
    private TableColumn<Resto, String> descriptionResto;
    @FXML
    private TableColumn<Resto, String> adresseResto;
    @FXML
    private TableColumn<Resto, Integer> nbplace;
    @FXML
    private JFXTextField search1;
    @FXML
    private TableView<?> tablePlat1;

    @FXML
    private JFXTextField searchplat;
    @FXML
    private JFXTextField nomPTxt;
    @FXML
    private JFXTextField descriptionPTxt;
    @FXML
    private JFXTextField prixPTxt;
    @FXML
    private JFXTextField categoriePTxt;
    @FXML
    private JFXTextField searchresto;
    @FXML
    private JFXTextField marquePTxt;
    private JFXTextField idTxt;
    @FXML
    private TableColumn<?, ?> numTxt;
    @FXML
    private TableColumn<?, ?> objetTxt;
    @FXML
    private TableColumn<?, ?> categorieTxt;
    @FXML
    private TableColumn<?, ?> messageTxt;
    @FXML
    private TableColumn<?, ?> emailTxt;
    @FXML
    private TableColumn<?, ?> dateTxt;
    @FXML
    private TableColumn<?, ?> statutTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idResto.setCellValueFactory(new PropertyValueFactory<Resto, Integer>("id"));
        nomResto.setCellValueFactory(new PropertyValueFactory<Resto, String>("nom"));
        descriptionResto.setCellValueFactory(new PropertyValueFactory<Resto, String>("description"));
        adresseResto.setCellValueFactory(new PropertyValueFactory<Resto, String>("adresse"));
        nbplace.setCellValueFactory(new PropertyValueFactory<Resto, Integer>("nbrdeplace"));
        tableRestaurant.setItems(list);
    }

    RestoService ps = new RestoService();
    public ObservableList<Resto> list = FXCollections.observableArrayList(
            ps.afficher()
    );

    /* public ObservableList<Resto> list = FXCollections.observableArrayList(
      tableRestaurant.setItems(list));
      tableRestaurant.setRowFactory(tv -> {

             TableRow<Chauffeur> row = new TableRow<>();
             row.setOnMouseClicked(event -> {
                 if (!row.isEmpty()) {
                     Chauffeur rowData = row.getItem();
                     nomTxt.setText(rowData.getNom());
                     prenomTxt.setText(rowData.getPrenom());
                     cinTxt.setText(Integer.toString(rowData.getCin()));
                     current_id = rowData.getId();
                 }
             });
             return row;
         });
     */
    public void showNotif() {
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

                .onAction(s -> {
                    // System.out.println("notif clicked");
                });
        notif.show();

    }

    boolean validateFieldsResto() {
        if (nomTxt.getText().isEmpty() || descriptionTxt.getText().isEmpty() || AdresseTxt.getText().isEmpty() || NombredeplaceTxt.getText().isEmpty()) {
            showNotif();
            return false;
        }

        return true;
    }


    @FXML
    private void onAjouterRestoAction(ActionEvent event) {

        if (validateFieldsResto()) {
            int nbr = Integer.parseInt(NombredeplaceTxt.getText());
            Resto c = new Resto(nomTxt.getText(), descriptionTxt.getText(), AdresseTxt.getText(), nbr);
            RestoService sc = new RestoService();

            sc.ajouter(c);

            /**
             * refreshing the table view *
             */

            list.clear();
            list = FXCollections.observableArrayList(
                    sc.afficher()
            );

            tableRestaurant.setItems(list);
        }

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
