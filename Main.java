/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entities.*;
import Dao.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author user
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        DataBase data=new DataBase();
        data.openConnection(); 
        
        
        String sql="INSERT INTO classe(libelle) VALUES (?)";
        data.initPreparedStatement(sql);
        data.getPs().setString(1, "Dentist");
        //executer la requete
        //update ou delete
        int nbreLigne=data.executeUpdate(sql);
        //insert LastInsertId
        ResultSet rs=data.getPs().getGeneratedKeys();
        if(rs.next()){
            //Id généré
            System.out.println(rs.getInt(1));
        }
        
        data.closeConnection(); 
        
        /*
        List<Depatements> depatements= new ArrayList<>();
        String sql="SELECT * FROM depatement";
        data.initPreparedStatement(sql);
        ResultSet rs=data.executeSelect(sql);
        while(rs.next()){
            //Mapping Relation to Object
            Depatement dp=new Depatement(rs.getInt("id"),rs.getString("libelle"));
            depatements.add(dp);
        }
        data.closeConnection();
        
        depatements.forEach(System.out::println);*/
    }
    
}
