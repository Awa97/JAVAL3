/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Dto.PatientDTO;
import Entities.Classe;
import Entities.Patient;
import Services.Service;
import Utils.ViewService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author user
 */
public class InscriptionController implements Initializable {
    
    private Patient patientSearch;
    Service service = new Service();
    ObservableList<Classe> obClasse;
    ObservableList<EtudiantDTO> obPatDTO;

    @FXML
    private TextField matricule;
    @FXML
    private TextField tuteur;
    @FXML
    private TextField nomComplet;
    @FXML
    private ComboBox<Classe> cboClasse;
    @FXML
    private TableView<PatientDTO> tblConsultation;
    @FXML
    private TableColumn<PatientDTO, String> tblMat;
    @FXML
    private TableColumn<PatientDTO, String> tblNom;
    @FXML
    private TableColumn<PatientDTO, String> tblClasse;
    @FXML
    private Text txtMat;
    @FXML
    private Text txtNom;
    @FXML
    private ComboBox<String> cboSearch;
    @FXML
    private ComboBox<Classe> cboSearchClasse;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String anneeEnCours= HomeController.getCtrl().getAnneeEnCours();
        List<PatientDTO> patients =service.searchConsultation(anneeEnCours);
        
        loadComboBoxClasse(cboClasse);
        loadComboBoxClasse(cboSearchClasse);
        loadTableView(patients);
        ViewService.loadComboBoxAnnee(cboSearch);
    }    

    private void loadComboBoxClasse(ComboBox<Classe> cbo){
        obClasse=FXCollections.observableArrayList(service.showAllClasse());
        cbo.setItems(obClasse);
    }
    
    @FXML
    private void handleAnnuler(ActionEvent event) {
        disabledField(false);
        clearField();
    }

    @FXML
    private void handleEnregistrer(ActionEvent event) {
        
        Classe classe=cboClasse.getSelectionModel().getSelectedItem();
        String anneeEnCours=HomeController.getCtrl().getAnneeEnCours();
        if(etudiantSearch==null){
            String mat=matricule.getText().trim();
            String nom=nomComplet.getText().trim();
            patientSearch= new Patient(mat, tut,nom);
        }
        int id=service.addConsultation(patientSearch, classe, anneeEnCours);
        patientSearch.setId(id);
        Alert alert= new Alert(AlertType.INFORMATION);
        alert.setTitle("consultation");
        alert.setContentText("consultation reussie");
        alert.show();
        
        PatientDTO patientDto= new EtudiantDTO();
        patientDto.toDTO(etudiantSearch);
        patientDto.setClasse(classe);
        obPatDTO.add(patientDto);
        patientSearch=null;
    }

    @FXML
    private void handleSearch(MouseEvent event) {
        String mat=matricule.getText();
        etudiantSearch= service.searchEtudiantByMatricule(mat);
        if(patientSearch!=null){
            nomComplet.setText(patientSearch.getNomComplet());
            tuteur.setText(patientSearch.getTuteur());
            disabledField(true);
        }else{
            clearField();
            disabledField(false);
        }
    }
    
    private void disabledField(boolean value){
        nomComplet.setDisable(value);
        
    }
    
    private void clearField(){
        matricule.clear();
        nomComplet.clear();
    
    }
    private void loadTableView(List<PatientDTO> listPatientDto){
        obEtuDTO=FXCollections.observableArrayList(listPatientDto);
        tblMat.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        tblNom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));
        tblConsultation.setItems(obPatDTO);
        
    }

    @FXML
    private void handleSearchPatienttByAnneeOrClasse(MouseEvent event) {
        Classe classe=cboSearchClasse.getSelectionModel().getSelectedItem();
        String annee= cboSearch.getSelectionModel().getSelectedItem();
        List<PatientDTO> patients= service.searchConsultation(annee, classe);
        loadTableView(etudiants);
    }
    
    
}
