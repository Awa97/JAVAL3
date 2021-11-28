/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Utils.ViewService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeController implements Initializable {
    
    private static HomeController ctrl;
    private String anneeEnCours;

    @FXML
    private Text txtProfil;
    @FXML
    private Text txtBienvenue;
    @FXML
    private ComboBox<String> cboAnnee;
    @FXML
    private AnchorPane anchorContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        txtProfil.setText("Profil, "+ ConnexionController.getCtrl().getUser().getRole());
        try {
            loadView("v_classe");
            ctrl=this;
            ViewService.loadComboBoxAnnee(cboAnnee);
            anneeEnCours=cboAnnee.getSelectionModel().getSelectedItem();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void loadView(String view)throws IOException{
        AnchorPane root = FXMLLoader.load(getClass().getResource("/views/"+view+".fxml"));
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }


    @FXML
    private void handleLoadViewAcceuil(ActionEvent event) throws IOException {
        loadView("v_classe");
    }

    @FXML
    private void handleLoadViewConnexion(ActionEvent event) throws IOException{
        //cacher la fenetre de connexion
                this.txtProfil.getScene().getWindow().hide();
                //ouvrir la fenetre du home
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                }
        //loadView("v_connexion");
    }

    @FXML
    private void handleLoadViewInscription(ActionEvent event) throws IOException{
        loadView("v_inscription");
    }

    @FXML
    private void handleLoadViewClasse(ActionEvent event) throws IOException{
        loadView("v_classe");
    }

    @FXML
    private void handleLoadViewAffectation(ActionEvent event) throws IOException{
        loadView("v_affectation");
    }

    @FXML
    private void handleChangeAnne(ActionEvent event) {
        anneeEnCours=cboAnnee.getSelectionModel().getSelectedItem();
    }

    public static HomeController getCtrl() {
        return ctrl;
    }

    public String getAnneeEnCours() {
        return anneeEnCours;
    }
    
    
}
