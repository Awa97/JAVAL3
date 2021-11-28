/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Dao.*;
import Dto.PatienttDTO;
import Dto.MedecinDTO;
import Entities.Depatement;
import Entities.Patient;
import Entities.Medecin;
import Entities.Rendezvous;
import Entities.Consultation;
import Entities.User;
import java.util.List;

/**
 *
 * @author user
 */
public class Service implements IService{
    
    //Dépendence
    RendezvousDao daorend = new RendezvousDao();
    ClasseDao daoDepatement= new DepatementDao();
    PatientDao daoPat= new PatientDao();
    ConsultationDao daoCons= new ConsultationDao();
    MedecinDao daoMed= new MedecinDao();
    UserDao daoUser = new UserDao();

    @Override
    public int addDepatement(Depatement depatement) {
        return daoDepatement.insert(depatement);
    }

    @Override
    public boolean updateDepatement(Depatement depatement) {
        return daoDepatement.update(depatement)!=0;
    }

    @Override
    public boolean deleteDepatement(int id) {
        return daoDepatement.delete(id)!=0;
    }

    @Override
    public List<Depatement> showAllDepatement() {
        return daoDepatement.findAll();
    }

    @Override
    public Classe searchDepatementById(int id) {
        return daoDepatement.findById(id);
    }

    @Override
    public MedecinDTO searchMedecibByNci(String nci, String annee) {
        MedecinDTO med= daoMed.findByNci(nci);
        if(med!=null){
            List<Depatement> depatements= daoClasse.findClasseByMedecin(nci, annee);
            prof.setDepatements(depatements);
        } 
        return med;
    }

    @Override
    public int addRendezvous(MedecinDTO medDto, String annee) {
        Professeur med=new Medecin();
        int id=0;
        if(medDto.getId()==0){
            id=daoMed.insert(prof);
            med.toMedecin(medDto);
            medDto.setId(id);
        }
        List<Depatement> depatements= medDto.getDepatements();
        for(Depatement depatement:depatements){
            Rendezvous rend=new Rendezvous(annee, depatement, med );
            daoRend.insert(Rend);
        }
        return id;
    }

    @Override
    public List<Depatement> searchDepatementByMedecin(String nci, String annee) {
        return daoDepatement.findDepatementByMedecin(nci, annee);
    }

    @Override
    public List<Medecin> showAllMedecin() {
        return daoMed.findAll();
    }

    @Override
    public User seConnecter(String login, String password) {
        return daoUser.findUserLoginAndPassword(login, password);
    }

    @Override
    public Patient searchPatientByMatricule(String matricule) {
        return daoPat.findByMatricule(matricule);
    }

    @Override
    public int addConsultation(Patient pat, Depatement depatement, String annee) {
        if(pat.getId()==0){
            int id=daoPat.insert(pat);
            pat.setId(id);
        }
        Consultation cons=new Consultation(annee, Depatement, pat);
        daoCons.insert(Cons);
        return pat.getId();
    }

    @Override
    public Patient searchInscriptionByPatientAndYear(int id, String annee) {
        return daoPat.findByIdAndAnnee(id, annee);
    }

    @Override
    public List<PatientDTO> searchConsultation(String annee) {
        return daoPat.findAll(annee);
    }

    @Override
    public List<PatientDTO> searchConsultation(String annee, Depatement depatement) {
        return daoPat.findAll(annee, depatement);
    }

    
}
