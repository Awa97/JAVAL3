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
public class Rendezvous {
    
    private int id;
    private String annee;
    //attribut navigationnel
    private Depatement depatement;
    private Medecin medecin;

    public Rendezvous() {
    }
    
    //persist
    public Rendezvous(String annee, Depatement depatement, Medecin medecin) {
        this.annee = annee;
        this.depatement = depatement;
        this.medecin = medecin;
    }
    
    //update
    public Rendezvous(int id, String annee, Depatement depatement, Medecin medecin) {
        this.id = id;
        this.annee = annee;
        this.depatement = depatement;
        this.medecin = medecin;
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

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
    
    
    
    
    
    
    
}
