/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Dto.MedecinDTO;

/**
 *
 * @author user
 */
public class Medecin extends User{
    
    private String nci;
    private String grade;

    public Medecin() {
        this.role="ROLE_MEDECIN";
    }

    //to persist
    public Medecin(String nci, String grade, String nomComplet) {
        super(nomComplet);
        this.nci = nci;
        this.grade = grade;
        this.role="ROLE_MEDECIN";
    }

    //to update
    public Medecin(String nci, String grade, int id, String nomComplet) {
        super(id, nomComplet);
        this.nci = nci;
        this.grade = grade;
        this.role="ROLE_MEDECIN";
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public void toMedecin(MedecinDTO med){
        this.id=med.getId();
        this.setGrade(med.getGrade());
        this.setNci(med.getNci());
        this.setNomComplet(med.getNomComplet());
    }
    
    
}
