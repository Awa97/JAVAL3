/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Dto.MedecinDTO;
import Entities.Medecin;
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
public class MedecinDao implements IDao<Medecin>{
    
    private DataBase database= new DataBase();
    private final String SQL_SELECT_MED_BY_NCI="SELECT * FROM user WHERE nci like ?";
    private final String SQL_ALL="SELECT * FROM user WHERE role LIKE 'ROLE_MEDECIN'";
    private final String SQL_INSERT="INSERT INTO user ('nomComplet','role', nci','grade') VALUES (?,'ROLE_PROFESSEUR',?,?)";
 

    @Override
    public int insert(Medecin med) {
        int id=0;
        try {
            database.openConnection();
            database.initPreparedStatement(SQL_INSERT);
            database.getPs().setString(1, med.getNomComplet());
            database.getPs().setString(2, med.getNci());
            database.getPs().setString(3, med.getGrade());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return id;
    }

    @Override
    public int update(Medecin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        List<Medecin> meds = new ArrayList();
        try {
            database.openConnection();
            database.initPreparedStatement(SQL_ALL);
            ResultSet rs= database.executeSelect(SQL_ALL);
            while(rs.next()){
                try {
                    Medecin med=new Medecin();
                    med.setId(rs.getInt("id"));
                    med.setNomComplet(rs.getString("nomComplet"));
                    med.setGrade(rs.getString("grade"));
                    med.setNci(rs.getString("nci"));
                    meds.add(med);
                } catch (SQLException ex) {
                    Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            database.closeConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return meds;
    }

    @Override
    public Medecin findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public MedecinDTO findByNci(String nci){
        MedecinDTO prof=null;
        database.openConnection();
        database.initPreparedStatement(SQL_SELECT_MED_BY_NCI);
        try {
            database.getPs().setString(1, nci);
            ResultSet rs=database.executeSelect(SQL_SELECT_MED_BY_NCI);
            if(rs.next()){
                med=new MedecinDTO(rs.getInt("id"),rs.getString("nci"), rs.getString("nomComplet"), rs.getString("grade"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();      
        }
        return med;
    }

    
}
