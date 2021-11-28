/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Dto.MedecinDTO;
import Entities.Classe;
import Entities.Medecin;
import Services.Service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
public class RendezvousController implements Initializable, IController {
    
    private MedecinDTO medSelected;
    private String anneeEnCours= HomeController.getCtrl().getAnneeEnCours();
    private final Service service=new Service();
    private ObservableList<Classe> obClasses;
    private ObservableList<Classe> obClassesTableView= FXCollections.observableArrayList() ;
    private ObservableList<Professeur> obProf;


    @FXML
    private ComboBox<Classe> cboClasse;
    @FXML
    private TableView<Classe> tblClasse;
    @FXML
    private TableColumn<Classe, String> tblId;
    @FXML
    private TableColumn<Classe, String> tblLibelle;
    @FXML
    private TextField txtNCI;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtGrade;
    @FXML
    private TableView<Professeur> tblProfesseur;
    @FXML
    private TableColumn<Professeur, String> tblNCI;
    @FXML
    private TableColumn<Professeur, String> tblNom;
    @FXML
    private TableColumn<Professeur, String> tblGrade;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadComboBoxClasse(cboClasse);
        loadTableView();
        loadTableViewProf();
    }    
    
    private void loadComboBoxClasse(ComboBox<Classe> cbo)
    {
      obClasses = FXCollections.observableArrayList(service.showAllClasse());
      cbo.setItems(obClasses);
    }

    @FXML
    private void handleAddClassProf(MouseEvent event) {
        Classe classe=cboClasse.getSelectionModel().getSelectedItem();
        obClassesTableView.add(classe);
    }
    
    private void loadTableView(){
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblClasse.setItems(obClassesTableView);
    }

    @FXML
    private void handleSearchMedByNci(MouseEvent event) {
        String nci=txtNCI.getText().trim();
        medSelected = service.searchMedecinByNci(nci, anneeEnCours);
        if(medSelected==null){
            disableFields(false);
            clearFields();
            obClassesTableView.clear();
        }else{
            txtNCI.setText(medSelected.getNci());
            txtNom.setText(medSelected.getNomComplet());
            txtGrade.setText(medSelected.getGrade());
            disableFields(true);
            obClassesTableView= FXCollections.observableArrayList(medSelected.getClasses());
            loadTableView();

        }
    }

    @FXML
    private void handleReset(ActionEvent event) {
        disableFields(false);
        clearFields();
        obClassesTableView.clear();
    }

    @FXML
    private void handleAffectationProf(ActionEvent event) {
        if(medSelected==null){
            String nci=txtNCI.getText().trim();
            String nomComplet=txtNom.getText().trim();
            String grade=txtGrade.getText().trim();
            medSelected=new MedecinDTO(nci, nomComplet,grade);
            int id=service.addAffectation(medSelected, anneeEnCours);
            medSelected.setId(id);
            Medecin med=new Medecin();
            med.toMedecin(medSelected);
            obMed.add(med);
        }
        
        
    }
    
    private void loadTableViewMed(){
        obMed=FXCollections.observableArrayList(service.showAllMedecin());
        tblNCI.setCellValueFactory(new PropertyValueFactory<>("nci"));
        tblNom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        tblGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        tblProfesseur.setItems(obProf);
    }

    @Override
    public void disableFields(boolean param) {
        //txtNCI.setDisable(param);
        txtNom.setDisable(param);
        txtGrade.setDisable(param);
    }

    @Override
    public void clearFields() {
        txtNCI.clear();
        txtNom.clear();
        txtGrade.clear();
    }
}
