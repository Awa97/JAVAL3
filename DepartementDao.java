/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entities.Departement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class DepartementDao implements IDao<Departement>{
    DataBase database=new DataBase();
    /*
    requete sql
    */
    private final String SQL_INSERT="INSERT INTO depatement(libelle) VALUES (?)";
    private final String SQL_UPDATE="UPDATE depatement SET libelle=? WHERE id=? ";
    private final String SQL_DELETE="DELETE FROM depatement WHERE id=?";
    private final String SQL_ALL="SELECT * FROM depatement";
    private final String SQL_BY_ID="SELECT * FROM depatement WHERE id=?";
    private final String SQL_BY_PROF_ANNEE="SELECT * FROM depatement d, rendezvous r, user u"
                                            +" WHERE c.id = a.depatement_id AND a.med_id= u.id"
                                            +" AND a.annee like ?"
                                            +" AND u.nci like ?";
    
    @Override
    public int insert(Depatement depatement) {
        int lastInsertId=0;
        database.openConnection();
            database.initPreparedStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, depatement.getLibelle());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
                lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepatementDao.depatement.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnection();
        return lastInsertId;
    }

    @Override
    public int update(Depatement depatement) {
        int nbreLigne=0;
        database.openConnection();
            database.initPreparedStatement(SQL_UPDATE);
        try {
            database.getPs().setString(1, depatement.getLibelle());
            database.getPs().setInt(2, depatement.getId());
            nbreLigne=database.executeUpdate(SQL_UPDATE);   
        } catch (SQLException ex) {
            Logger.getLogger(DepatementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnection();
        return nbreLigne;
    }

    @Override
    public int delete(int id) {
        int nbreLigne=0;
        database.openConnection();
            database.initPreparedStatement(SQL_DELETE);
        try {
            database.getPs().setInt(1, id);
            nbreLigne=database.executeUpdate(SQL_DELETE);   
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnection();
        return nbreLigne;
    }

    @Override
    public List<Depatement> findAll() {
        List<Depatement> depatements= new ArrayList<>();
        database.openConnection();
            database.initPreparedStatement(SQL_ALL);
        try {
           
            ResultSet rs=database.executeSelect(SQL_ALL);
            while(rs.next()){
                //mappig relationnel vers object
                Depatement depatement= new Depatement(rs.getInt("id"), rs.getString("libelle"));
                depatements.add(classe);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepatementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnection();
        return classes;
    }

    @Override
    public Depatement findById(int id) {
        Depatement depatement=null;
        database.openConnection();
            database.initPreparedStatement(SQL_BY_ID);
        try {
           
            ResultSet rs=database.executeSelect(SQL_BY_ID);
            if(rs.next()){
                //mappig relationnel vers object
                depatement= new Depatement(rs.getInt("id"), rs.getString("libelle"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepatementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnection();
        return classe;
    }
    
    public List<Depatement> findDepatementByMedecin(String nci, String annee){
        List<Depatement> depatements= new ArrayList<>();
        database.openConnection();
            database.initPreparedStatement(SQL_BY_PROF_ANNEE);
        try {
            database.getPs().setString(1, annee);
            database.getPs().setString(2,nci);
            ResultSet rs=database.executeSelect(SQL_BY_PROF_ANNEE);
            while(rs.next()){
                //mappig relationnel vers object
                Depatement depatement= new Depatement(rs.getInt("id"), rs.getString("libelle"));
                depatements.add(depatement);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepatementDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnection();
        return depatements;
    }

    
    
}
