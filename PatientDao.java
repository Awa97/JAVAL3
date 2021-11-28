/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.PatientDTO;
import Entities.Patient;
import Entities.Depatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class PatientDao implements IDao<Patient>{

    private final String SQL_SELECT_PATIENT_BY_MATRICULE="SELECT * FROM user WHERE matricule LIKE ?";
    private DataBase database=new DataBase();
    private final String SQL_SELECT_CONSULTATION_BY_ANNEE="SELECT * FROM user e, consultation c, depatement d WHERE e.id=i.etudiant_id AND i.classe_id = c.id AND i.annee like ?";
    private final String SQL_INSERT="INSERT INTO `user` (`nomComplet`,`role`, `matricule`, `tuteur`) VALUES (?, 'ROLE_ETUDIANT',?, ?)";
    private final String SQL_SELECT_INSCRITS_BY_ANNEE_OR_DEPARTEMENT="SELECT * FROM user e, consultaion c, depatement d WHERE e.id=i.etudiant_id AND i.classe_id = c.id AND i.annee like ? AND c.id like ?";
    
    @Override
    public int insert(Patient patient) {
        int idEtudiant=0;
        try {
            database.openConnection();
            database.initPreparedStatement(SQL_INSERT);
            database.getPs().setString(1,patient.getNomComplet());
            database.getPs().setString(2,patient.getMatricule());
            int nbrLigne=database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                idPatient=rs.getInt(1);
            }
            database.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idPatient;
    }

    @Override
    public int update(Patient p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Patient findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Patient findByMatricule(String matricule) {
        Patient pat=null;
        database.openConnection();
            database.initPreparedStatement(SQL_SELECT_PATIENT_BY_MATRICULE);
        try {
            database.getPs().setString(1, matricule);
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            ResultSet rs= database.executeSelect(SQL_SELECT_PATIENT_BY_MATRICULE);
        try {
            if(rs.next()){
                pat=new Patient();
                pat.setId(rs.getInt("id"));
                pat.setNomComplet(rs.getString("nomComplet"));
                pat.setMatricule(rs.getString("matricule"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnection();
        
        return pat;
    }
    
    public Patient findByIdAndAnnee(int id, String annee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<PatientDTO> findAll(String annee) {
        List<PatientDTO> patients= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_CONSULTATION_BY_ANNEE);
        try {
            database.getPs().setString(1, annee);
            ResultSet rs= database.executeSelect(SQL_SELECT_CONSULTATION_BY_ANNEE);
            while(rs.next()){
                PatientDTO pat=new PatientDTO();
                pat.setId(rs.getInt("id"));
                pat.setNomComplet(rs.getString("nomComplet"));
                pat.setMatricule(rs.getString("matricule"
                Depatement dp= new Depatement();
                dp.setId(rs.getInt("classe_id"));
                dp.setLibelle(rs.getString("libelle"));
                pat.setClasse(cl);
                patients.add(etu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnection();
        return patients;
    }
    
    public List<PaientDTO> findAll(String annee, Depatement depatement) {
        List<PatientDTO> patients= new ArrayList();
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_CONSULTATION_BY_ANNEE_OR_DEPARTEMENT);
        try {
            database.getPs().setString(1, annee);
            database.getPs().setInt(2, classe.getId());
            ResultSet rs= database.executeSelect(SQL_SELECT_INSCRITS_BY_ANNEE_OR_CLASSE);
            while(rs.next()){
                PatientDTO pat=new PatientDTO();
                pat.setId(rs.getInt("id"));
                pat.setNomComplet(rs.getString("nomComplet"));
                pat.setMatricule(rs.getString("matricule"));
                Depatement dp= new Classe();
                dp.setId(rs.getInt("classe_id"));
                dp.setLibelle(rs.getString("libelle"));
                pat.setDepatement(dp);
                patients.add(pat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnection();
        return patients;
    }

    @Override
    public List<Patient> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
