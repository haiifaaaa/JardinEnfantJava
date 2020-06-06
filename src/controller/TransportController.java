/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import com.sun.prism.impl.Disposer;
import entities.Bus;
import entities.Chauffeur;
import entities.Ligne;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
import services.BusService;
import services.ChauffeurService;
import services.LigneService;

/**
 * FXML Controller class
 *
 * @author asus_pc
 */
public class TransportController implements Initializable {
    @FXML
    private JFXTextField search;

    @FXML
    private DatePicker dateDepartPicker;

    @FXML
    private TableColumn<Bus, String> matriculeBusLigne;
    @FXML
    private TableColumn<Bus, String> marqueBusLigne;
    @FXML
    private TableView<Bus> tableBusLigne;

    @FXML
    private TableView<Ligne> tableLigne;

    @FXML
    private TableColumn<Ligne, String> nomLigne;
    @FXML
    private TableColumn<Ligne, String> pointDepart;
    @FXML
    private TableColumn<Ligne, String> pointArriver;
    @FXML
    private TableColumn<Ligne, Date> dateDepart;

    @FXML
    private TableColumn<Ligne, Integer> placesDispo;
    @FXML
    TableColumn actionLigne;
    @FXML
    private JFXTextField pointDepartTxt;
    @FXML
    private JFXTextField poitArriveTxt;
    @FXML
    private JFXTextField NomLigneTxt;
    @FXML
    private JFXTextField placeDispoTxt;

    @FXML
    private JFXTextField matriculeTxt;
    @FXML
    private JFXTextField marqueTxt;
    @FXML
    private TableView<Bus> tableBus;
    @FXML
    private TableView<Chauffeur> tableChauffeur;
    @FXML
    private TableView<Chauffeur> tableChauffeurBus;
    @FXML
    TableColumn<Bus, String> matricule;
    @FXML
    TableColumn<Bus, String> marque;
    @FXML
    TableColumn actionBus;
    @FXML
    TableColumn<Chauffeur, String> nom;
    @FXML
    TableColumn<Chauffeur, String> nomC;
    @FXML
    TableColumn<Chauffeur, String> prenom;
    @FXML
    TableColumn<Chauffeur, String> prenomC;
    @FXML
    TableColumn<Chauffeur, Integer> cin;
    @FXML
    TableColumn<Chauffeur, Integer> cinC;

    @FXML
    private TableColumn col_action;

    @FXML
    private JFXTextField nomTxt;

    @FXML
    private JFXTextField cinTxt;

    @FXML
    private JFXTextField prenomTxt;
    private int current_id;
    private int idChauffeur = -1;
    private int current_idBus;
    private int current_idLigne;
    private int current_idChauffeur;
    private int idBus = -1;
    /**
     * Initializes the controller class.
     */
    ChauffeurService ps = new ChauffeurService();
    public ObservableList<Chauffeur> list = FXCollections.observableArrayList(
            ps.afficher()
    );
    BusService bs = new BusService();
    public ObservableList<Bus> listBus = FXCollections.observableArrayList(
            bs.afficher()
    );
    LigneService ls = new LigneService();
    public ObservableList<Ligne> listLigne = FXCollections.observableArrayList(
            ls.afficher()
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      FilteredList<Chauffeur> filteredData = new FilteredList<>(list, e -> true);

        search.setOnKeyReleased(e -> {
            search.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Chauffeur>) chauffeur -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (chauffeur.getNom().toLowerCase().contains(lower)) {
                        return true;
                    }

                    return false;
                });
            });
            SortedList<Chauffeur> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tableChauffeur.comparatorProperty());
            tableChauffeur.setItems(sortedData);
        });
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomC.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cinC.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nomLigne.setCellValueFactory(new PropertyValueFactory<>("nom"));
        pointDepart.setCellValueFactory(new PropertyValueFactory<>("pointDepart"));
        pointArriver.setCellValueFactory(new PropertyValueFactory<>("pointArrive"));
        dateDepart.setCellValueFactory(new PropertyValueFactory<>("dateDepart"));
        placesDispo.setCellValueFactory(new PropertyValueFactory<>("placesDisponibles"));
        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        matriculeBusLigne.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        marqueBusLigne.setCellValueFactory(new PropertyValueFactory<>("marque"));
        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell();
            }

        });

        actionLigne.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        actionLigne.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell2();
            }

        });

        actionBus.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Disposer.Record, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Disposer.Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        actionBus.setCellFactory(
                new Callback<TableColumn<Disposer.Record, Boolean>, TableCell<Disposer.Record, Boolean>>() {

            @Override
            public TableCell<Disposer.Record, Boolean> call(TableColumn<Disposer.Record, Boolean> p) {
                return new ButtonCell1();
            }

        });

        tableChauffeur.setItems(list);
        tableChauffeurBus.setItems(list);
        tableBus.setItems(listBus);
        tableLigne.setItems(listLigne);
        tableBusLigne.setItems(listBus);
        tableChauffeur.setRowFactory(tv -> {
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
        tableChauffeurBus.setRowFactory(tv -> {
            TableRow<Chauffeur> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Chauffeur rowData = row.getItem();
                    idChauffeur = rowData.getId();
                }
            });
            return row;
        });
        tableBusLigne.setRowFactory(tv -> {
            TableRow<Bus> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Bus rowData = row.getItem();
                    idBus = rowData.getId();
                }
            });
            return row;
        });
        tableBus.setRowFactory(tv -> {
            TableRow<Bus> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Bus rowData = row.getItem();
                    matriculeTxt.setText(rowData.getMatricule());
                    marqueTxt.setText(rowData.getMarque());
                    current_idBus = rowData.getId();
                    current_idChauffeur = rowData.getIdChauffeur();
                }
            });
            return row;
        });
        tableLigne.setRowFactory(tv -> {
            TableRow<Ligne> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Ligne rowData = row.getItem();
                    pointDepartTxt.setText(rowData.getPointDepart());
                    poitArriveTxt.setText(rowData.getPointArrive());
                    NomLigneTxt.setText(rowData.getNom());
                    placeDispoTxt.setText(Integer.toString(rowData.getPlacesDisponibles()));
                    current_idBus = rowData.getBus_id();
                    current_idLigne = rowData.getId();
                    Instant instant = Instant.ofEpochMilli((rowData.getDateDepart()).getTime());
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    LocalDate localDate = localDateTime.toLocalDate();

                    dateDepartPicker.setValue(localDate);
                }
            });
            return row;
        });

    }

    private class ButtonCell extends TableCell<Disposer.Record, Boolean> {

        final Button cellButton = new Button("Delete");

        ButtonCell() {

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    //confirmation alert 
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("delete Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("are you sure ?");

                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        Chauffeur currentChauffeur = (Chauffeur) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());

                        ChauffeurService ps = new ChauffeurService();
                        int bus_id = ps.getBusID(currentChauffeur.getId());
                        System.out.println("bus_id = " + bus_id);
                        ps.setChauffeurNull(bus_id);
                        ps.deleteLigne(bus_id);
                        ps.supprimer(currentChauffeur);
                        list.remove(currentChauffeur);
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                cellButton.getStyleClass().add("btn");
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }

    private class ButtonCell1 extends TableCell<Disposer.Record, Boolean> {

        final Button cellButton1 = new Button("Delete");

        ButtonCell1() {

            //Action when the button is pressed
            cellButton1.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    //confirmation alert 
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("delete Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("are you sure ?");

                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        Bus currentBus = (Bus) ButtonCell1.this.getTableView().getItems().get(ButtonCell1.this.getIndex());

                        BusService ps = new BusService();
                        ps.supprimer(currentBus);
                        listBus.remove(currentBus);
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                cellButton1.getStyleClass().add("btn");
                setGraphic(cellButton1);
            } else {
                setGraphic(null);
            }
        }
    }

    private class ButtonCell2 extends TableCell<Disposer.Record, Boolean> {

        final Button cellButton2 = new Button("Delete");

        ButtonCell2() {

            //Action when the button is pressed
            cellButton2.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    //confirmation alert 
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("delete Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("are you sure ?");

                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {
                        Ligne currentLigne = (Ligne) ButtonCell2.this.getTableView().getItems().get(ButtonCell2.this.getIndex());
                        LigneService ps = new LigneService();
                        ps.supprimer(currentLigne);
                        listLigne.remove(currentLigne);
                    }
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                cellButton2.getStyleClass().add("btn");
                setGraphic(cellButton2);
            } else {
                setGraphic(null);
            }
        }
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
    boolean validateFieldsChauffeur(){
        if(cinTxt.getText().isEmpty()||nomTxt.getText().isEmpty()||prenomTxt.getText().isEmpty()){
        showNotif();
        return false;}
        
    return true;
    }
    
    @FXML
    void onAjouterAction(ActionEvent event) {
        if(validateFieldsChauffeur()){
        int cin = Integer.parseInt(cinTxt.getText());
        Chauffeur c = new Chauffeur(nomTxt.getText(), prenomTxt.getText(), cin);
        ChauffeurService sc = new ChauffeurService();
        sc.ajouter(c);
        /**
         * refreshing the table view *
         */
        list.clear();
        list = FXCollections.observableArrayList(
                sc.afficher()
        );
        tableChauffeur.setItems(list);
        tableChauffeurBus.setItems(list);
        }
    }

    @FXML
    void onModifierChauffeurAction(ActionEvent event) {
        ChauffeurService ps = new ChauffeurService();

        int cin = Integer.parseInt(cinTxt.getText());
        Chauffeur p = new Chauffeur(current_id, nomTxt.getText(), prenomTxt.getText(), cin);
        System.out.println("chauffeur = " + p);
        ps.modifier(p);
        /**
         * refreshing the table view *
         */
        list.clear();
        list = FXCollections.observableArrayList(
                ps.afficher()
        );
        tableChauffeur.setItems(list);
        tableChauffeurBus.setItems(list);
    }

    @FXML
    void onModifierBusAction(ActionEvent event) {
        Bus b;
        if (idChauffeur != -1) {
            b = new Bus(current_idBus, matriculeTxt.getText(), marqueTxt.getText(), idChauffeur);
        } else {
            b = new Bus(current_idBus, matriculeTxt.getText(), marqueTxt.getText(), current_idChauffeur);
        }
        BusService bs = new BusService();
        bs.modifier(b);
        /**
         * refreshing the table view *
         */
        listBus.clear();
        listBus = FXCollections.observableArrayList(
                bs.afficher()
        );
        tableBus.setItems(listBus);
        tableBusLigne.setItems(listBus);

    }
    boolean validateFieldsBus(){
    if(matriculeTxt.getText().isEmpty()||marqueTxt.getText().isEmpty()){
        showNotif();
        return false;
    }
    return true;
    }
    @FXML
    void onAjouterBusAction(ActionEvent event) {
        if(validateFieldsBus()){
        if (idChauffeur != -1) {
            Bus b = new Bus(matriculeTxt.getText(), marqueTxt.getText(), idChauffeur);
            BusService bs = new BusService();
            bs.ajouter(b);
            /**
             * refreshing the table view *
             */
            listBus.clear();
            listBus = FXCollections.observableArrayList(
                    bs.afficher()
            );
            tableBus.setItems(listBus);
            tableBusLigne.setItems(listBus);

        }
        }
    }
    boolean validatefieldsLigne(){
    if(pointDepartTxt.getText().isEmpty()||poitArriveTxt.getText().isEmpty()||NomLigneTxt.getText().isEmpty()||dateDepartPicker.getValue()==null)
    {
    showNotif();
    return false;
    }
    return true;
    }
    @FXML
    void onAjouterLigneAction(ActionEvent event) {
        if(validatefieldsLigne()){
        if (idBus != -1) {
            ZoneId defaultZoneId = ZoneId.systemDefault();

            //local date + atStartOfDay() + default time zone + toInstant() = Date
            Date date = Date.from(dateDepartPicker.getValue().atStartOfDay(defaultZoneId).toInstant());
            int nbPlaces = Integer.parseInt(placeDispoTxt.getText());
            Ligne l = new Ligne(idBus, pointDepartTxt.getText(), poitArriveTxt.getText(), date, NomLigneTxt.getText(), nbPlaces);
            LigneService bs = new LigneService();
            bs.ajouter(l);
            /**
             * refreshing the table view *
             */
            listLigne.clear();
            listLigne = FXCollections.observableArrayList(
                    bs.afficher()
            );
            tableLigne.setItems(listLigne);
}
        }

    }

    @FXML
    void onModifierLigneAction(ActionEvent event) {
         Ligne l;
         ZoneId defaultZoneId = ZoneId.systemDefault();
         Date date = Date.from(dateDepartPicker.getValue().atStartOfDay(defaultZoneId).toInstant());

        if (idBus != -1) {
            l = new Ligne(current_idLigne,idBus, pointDepartTxt.getText(),poitArriveTxt.getText(),date,NomLigneTxt.getText(),Integer.parseInt(placeDispoTxt.getText()));
        } else {
            l = new Ligne(current_idLigne,current_idBus, pointDepartTxt.getText(),poitArriveTxt.getText(),date,NomLigneTxt.getText(),Integer.parseInt(placeDispoTxt.getText()));
        }
        LigneService bs = new LigneService();
        bs.modifier(l);
        /**
         * refreshing the table view *
         */
        listLigne.clear();
        listLigne = FXCollections.observableArrayList(
                bs.afficher()
        );
        tableLigne.setItems(listLigne);

    }

}
