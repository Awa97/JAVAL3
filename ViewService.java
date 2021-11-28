/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javafx.scene.control.ComboBox;

/**
 *
 * @author user
 */
public class ViewService {
    public static void loadComboBoxAnnee(ComboBox<String> cboAnnee){
        cboAnnee.getItems().add("2021-2022");
        cboAnnee.getItems().add("2020-2021");
        cboAnnee.getItems().add("2019-2020");
        cboAnnee.getItems().add("2018-2019");
        cboAnnee.getSelectionModel().selectFirst();
    }
    
}
