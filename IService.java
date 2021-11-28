/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Dto.PatientDTO;
import Dto.medecinDTO;
import Entities.*;
import java.util.List;

/**
 *
 * @author user
 */
public interface IService {
    /*
        ajout=> booleen
    */
    
    /*
    Gerer classe
    */
    public int addDepatement(Depatement depatement);
    public boolean updateDepatement(Depatement depatement);
    public boolean deleteClasse(int id);
    public List<Depatement> showAllDepatement();
    public Classe searchDepatementById(int id);
    
    
    /*
    Gerer med
    */
    public MedecinDTO searchProfesseurByNci(String nci, String annee);
    //public boolean addProfesseur(Medecin med);
    public int addrendezvous(MedecinDTO med, String annee);
    public List<Depatement> searchDepatementByMedecin(String nci, String annee);
    public List<Medecin> showAllMedecin();
    
    
    /*
    se connecter
    */
    public User seConnecter(String login, String password);

    /*
        consultation
    */
    public Patient searchPatientByMatricule(String matricule);
    public int addConsultation(Patient pat, Depatement depatement, String annee);
    public Patient searchConsultationByPatientAndYear(int id, String annee);
    public List<EtudiantDTO> searchConsultation(String annee);
    public List<EtudiantDTO> searchConsultation(String annee, Depatement depatement);


    
    
}
