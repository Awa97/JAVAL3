/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Rendezvous;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class RendezvousDao implements IDao<Rendezvous>{
    
    private DataBase database=new DataBase();
    private final String SQL_INSERT= "INSERT INTO rendezvous ('annee', 'id_prof', 'id_depatement') VALUES (?,?,?)";

    @Override
    public int insert(Rendezvous rendezvous) {
        int id=0;
        try {
            database.openConnection();
            database.initPreparedStatement(SQL_INSERT);
            database.getPs().setString(1, rendezvous.getAnnee());
            database.getPs().setInt(2, rendezvous.getMedecin().getId());
            database.getPs().setInt(3, rendezvous.getClasse().getId());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs= database.getPs().getGeneratedKeys();
            if(rs.next()){
                id=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RendezvousDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            database.closeConnection();
        }
        return id;
    }

    @Override
    public int update(Rendezvous t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rendezvous> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rendezvous findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
