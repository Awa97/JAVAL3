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
public class Consultation {
    
    private int id;
    private String annee;
    //attribut navigationnel
    private Depatement depatement;
    private Patient patient;

    public Consultation() {
    }

    //persist
    public Consultation(String annee, Depatement depatement, Patient patient) {
        this.annee = annee;
        this.depatement = depatement;
        this.patient = patient;
    }

    //update
    public Consultation(int id, String annee, Depatement depatement, Patient patientt) {
        this.id = id;
        this.annee = annee;
        this.depatement = depatement;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Depatement getDepatement() {
        return depatement;
    }

    public void setDepatement(Depatement depatement) {
        this.depatement = depatement;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
    
    
    
}
