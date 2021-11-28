/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Entities.User;
import Services.Service;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ConnexionController implements Initializable {

    @FXML
    private Text txtTitre;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtPassword;
    @FXML
    private Text txtErreur;
    
    private Service service=new Service();
    private User user;

    public User getUser() {
        return user;
    }

    public static ConnexionController getCtrl() {
        return ctrl;
    }
    private static ConnexionController ctrl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtErreur.setVisible(false);
        ctrl=this;
    }    

    @FXML
    private void handleClear(ActionEvent event) {
        txtLogin.clear();
        txtPassword.clear();
    }

    @FXML
    private void handleConnexion(ActionEvent event) {
        String login= txtLogin.getText().trim();
        String pwd=txtPassword.getText().trim();
        if(login.isEmpty() || pwd.isEmpty()){
            txtErreur.setText("Login/Password Obligatoire");
            txtErreur.setVisible(true);
        }else{
            user=service.seConnecter(login, pwd);
            if(user==null){
                txtErreur.setText("Erreur Login ou Password");
                txtErreur.setVisible(true);
            }else{
                //cacher la fenetre de connexion
                this.txtErreur.getScene().getWindow().hide();
                //ouvrir la fenetre du home
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/v_home.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage=new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
