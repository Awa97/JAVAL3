/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Consultation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConsultationDao implements IDao<Consultation>{
    
    private final String SQL_INSERT="INSERT INTO `Consultation` (`annee`, `patient_id`, `depatement_id`) VALUES (?, ?, ?)";
    private DataBase database=new DataBase();

    @Override
    public int insert(Consultatation consultation) {
        int idConsultation=0;
        try {
            database.openConnection();
            database.initPreparedStatement(SQL_INSERT);
            database.getPs().setString(1, consultation.getAnnee());
            database.getPs().setInt(2,consultation.getPatientt().getId());
            database.getPs().setInt(3,consultation.getDepatement().getId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                idConsultation=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return idConsultationtion;
    }

    @Override
    public int update(Consultation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
