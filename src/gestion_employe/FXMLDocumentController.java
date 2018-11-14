/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_employe;

import classe.Employe;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.EmployeService;

/**
 *
 * @author PC
 */
public class FXMLDocumentController implements Initializable {
    
    EmployeService es = new EmployeService();
    ObservableList<Employe> employeList = FXCollections.observableArrayList();
    private static int index;
    Date dt = new Date();
    
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField telephone;
    @FXML
    private DatePicker dateEmbauche;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TableView<Employe> employes;
    @FXML
    private TableColumn<Employe, String> cNom;
    @FXML
    private TableColumn<Employe, String> cPrenom;
    @FXML
    private TableColumn<Employe, String> cTelephone;
    @FXML
    private TableColumn<Employe, LocalDate> cDateEmbauche;
    @FXML
    private TableColumn<Employe, String> cEmail;
    @FXML
    private TableColumn<Employe, String> cPassword;
    @FXML
    
    private void saveAction(ActionEvent event) {
        String n = nom.getText().toString();
        String p = prenom.getText().toString();
        String t = telephone.getText().toString();
        LocalDate d = dateEmbauche.getValue();
        String e = email.getText();
        String ps = password.getText();
        Instant instant = Instant.from(d.atStartOfDay(ZoneId.systemDefault()));
        dt = Date.from(instant);
        es.create(new Employe(n, p, t,dt,e,ps ));
        load();
        clean();
        }
    @FXML
    private void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Vous vous vraiment supprimer cette machine?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(index));
            employeList.clear();
            load();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        load();
        employes.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) employes.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Employe item = employes.getItems().get(row);
                nom.setText(item.getNom());
                prenom.setText(item.getPrenom());
                telephone.setText(item.getTelephone());
                index = item.getId();
                Date date = item.getDateEmbauche();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                SimpleDateFormat sdf = new  SimpleDateFormat("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(sdf.format(date), formatter);
                dateEmbauche.setValue(localDate);
//                System.out.println(localDate.MIN);
//                load();
            }
                
                });
        
    }

    public void clean(){
        nom.setText("");
        prenom.setText("");
        telephone.setText("");
        dateEmbauche.setValue(LocalDate.MIN);
        email.setText("");
        password.setText("");
    }
    public void load(){
        employeList.clear();
        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        cDateEmbauche.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
//         for (Employe e : es.findAll()) {
//            employeList.add(new Employe(e.getId(), e.getNom(),e.getPrenom(),e.getTelephone(),e.getDateEmbauche(),e.getEmail(),e.getPassword() ));
//        }
         employes.setItems(employeList);
        
    }
}
