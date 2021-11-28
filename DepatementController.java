/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.Depatement;
import Services.Service;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DepatementController implements Initializable {
    
    private Service service=new Service();
    private ObservableList<Depatement> obDepatements;
    private Depatement depatementSelected;
    
    @FXML
    private TableView<Depatement> tlbvDepatement;
    @FXML
    private TableColumn<Depatement, String> tlbcId;
    @FXML
    private TableColumn<Depatement, String> tlbcLibelle;
    @FXML
    private TextField txtLibelle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableView();
    }    
    
    private void loadTableView(){
        List<Depatement> depatements= service.showAllDepatement();
        //observable
        obDepatements= FXCollections.observableArrayList(depatements);
        //abonnement
        tlbvDepatement.setItems(obDepatements);
        tlbcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tlbcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        
    }

    @FXML
    private void handleAddDepatement(ActionEvent event) {
        if(txtLibelle.getText().isEmpty()){
            Alert alert= new Alert(AlertType.ERROR);
            alert.setTitle("Depatement");
            alert.setContentText("Veuillez saisir le libellé");
            alert.show();
        }else{
            Depatement depatement=new Depatement(txtLibelle.getText());
            int id= service.addDepatement(depatement);
            depatement.setId(id);
            Alert alert= new Alert(AlertType.INFORMATION);
            alert.setTitle("Depatement");
            alert.setContentText("enregistrer avec succès");
            alert.show();
            obDepatements.add(depatement);
        }
        txtLibelle.clear();
    }

    @FXML
    private void handleUpdateDepatement(ActionEvent event) {
        if(txtLibelle.getText().isEmpty()){
            Alert alert= new Alert(AlertType.ERROR);
            alert.setTitle("Classe");
            alert.setContentText("Veuillez saisir le libellé");
            alert.show();
        }else{
            depatementSelected.setLibelle(txtLibelle.getText());
            if(service.updateDepatement(depatementSelected)){
                Alert alert= new Alert(AlertType.INFORMATION);
                alert.setTitle("Depatement");
                alert.setContentText("enregistrer avec succès");
                alert.show();
                obClasses.set(searchDepatement(depatementSelected), depatementSelected);
            }
        }
        txtLibelle.clear();
    }
    
    private int searchDepatement(Depatement Depatement){
        int pos=-1;
        for(Depatement dp:obDepatements){
            pos++;
            if(cl.getId()==depatement.getId()) return pos;
        }
        return pos;
    }

    @FXML
    private void handleDeleteDepatement(ActionEvent event) {
        txtLibelle.setDisable(true);
        Alert alert= new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Depatement");
        alert.setContentText("veuillez confirmer la suppresion");
        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            if(service.deleteDepatement(depatementSelected.getId())){
                Alert alert2= new Alert(AlertType.INFORMATION);
                alert2.setTitle("Depatement");
                alert2.setContentText("Depatement supprimer avec succès");
                alert2.show();
                //loadTableView();
                obDepatements.remove(searchDepatement(depatementSelected));
            }
        }
        txtLibelle.setDisable(false);
        txtLibelle.clear();
    }

    @FXML
    private void handleSelectDepatement(MouseEvent event) {
        depatementSelected=tlbvDepatement.getSelectionModel().getSelectedItem();
        txtLibelle.setText(depatementSelected.getLibelle());
    }
    
    
}
