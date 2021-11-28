/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dto;

import Entities.Depatement;
import Entities.Medecin;
import java.util.List;

/**
 *
 * @author user
 */
public class MedecinDTO {
    
    private int id;
    private String nci;
    private String nomComplet;
    private String grade;
    private List<Classe> classes;

    public MedecinDTO() {
    }

    public MedecinDTO(int id, String nci, String nomComplet, String grade, List<Depatemente> depatements) {
        this.id = id;
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.grade = grade;
        this.Depatements = depatements;
    }
    
    public MedecinDTO(int id, String nci, String nomComplet, String grade) {
        this.id = id;
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.grade = grade;
    }
    
    public MedecinDTO(String nci, String nomComplet, String grade) {
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.grade = grade;
    }

    public MedecinDTO(String nci, String nomComplet, String grade, List<Depatement> depatements) {
        this.nci = nci;
        this.nomComplet = nomComplet;
        this.grade = grade;
        this.depatements = depatements;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Depatement> getDepatements() {
        return depatements;
    }

    public void setClasses(List<Classe> depatements) {
        this.depatements = depatements;
    }
    
    
}
