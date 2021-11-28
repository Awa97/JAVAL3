/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author user
 */
public class Patient extends User{
    private String matricule;

    public Patient() {
        this.role="ROLE_PATIENT";
    }

    //to persist
    public Patient(String matricule, String tuteur, String nomComplet) {
        super(nomComplet);
        this.matricule = matricule;
        this.role="ROLE_PATIENT";
    }

    //to update
    public Patient(String matricule, int id, String nomComplet) {
        super(id, nomComplet);
        this.matricule = matricule;
        this.role="ROLE_PATIENT";
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    

    
}
