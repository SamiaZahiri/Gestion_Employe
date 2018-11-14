/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classe.Profile;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.ProfileService;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class ProfileVueController implements Initializable {
    ProfileService ps = new ProfileService();
    ObservableList<Profile> profilList = FXCollections.observableArrayList();
    private static int index;
    @FXML
    private TextField code;
    @FXML
    private TextField libelle;
    @FXML
    private TableView<Profile> profiles;
    @FXML
    private TableColumn<Profile, String> cCode;
    @FXML
    private TableColumn<Profile, String> cLibelle;
    
    @FXML
     private void saveAction(ActionEvent event) {
        String c = code.getText().toString();
        String l = libelle.getText().toString();
        
//        Instant instant = Instant.from(d.atStartOfDay(ZoneId.systemDefault()));
        
        ps.create(new Profile(c, l ));
//        load();
//        clean();
        }
     
     @FXML
    private void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Vous vous vraiment supprimer cette machine?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ps.delete(ps.findById(index));
            profilList.clear();
//            load();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        profiles.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                TablePosition pos = (TablePosition) profiles.getSelectionModel().getSelectedCells().get(0);
//                int row = pos.getRow();
//                Profile item = profiles.getItems().get(row);
//                code.setText(item.getCode());
//                libelle.setText(item.getLibelle());
//                index = item.getId();
//                }
//            });
//        
    }  
    
     public void clean(){
        code.setText("");
        libelle.setText("");
     }
     
//     public void load(){
//        profilList.clear();
//        cCode.setCellValueFactory(new PropertyValueFactory<>("code"));
//        cLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
//        for (Profile p : ps.findAll()) {
//            profilList.add(new Profile(p.getId(),p.getCode(),p.getLibelle()));
//        }
//         profiles.setItems(profilList);
//     }
}
