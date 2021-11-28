/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.Depatement;
import Entities.Patient;

/**
 *
 * @author user
 */
public class PatientDTO {
    private int id;
    private String NomComplet;
    private Depatement depatement;
    private String matricule;
    private String role;

    public PatientDTO() {
        
    }
    
    public void toDTO(Patient pat){
        this.id=etu.getId();
        this.NomComplet=pat.getNomComplet();
        this.matricule=pat.getMatricule();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomComplet() {
        return NomComplet;
    }

    public void setNomComplet(String NomComplet) {
        this.NomComplet = NomComplet;
    }

    public Depatement getDepatement() {
        return depatement;
    }

    public void setClasse(Depatement depatement) {
        this.depatement = depatement;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setTuteur(String tuteur) {
        this.tuteur = tuteur;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    
}
