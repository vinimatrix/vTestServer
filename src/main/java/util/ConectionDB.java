/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vinicio.mendez
 */
public class ConectionDB {
    private static EntityManagerFactory factory;
    
    private ConectionDB(){}
    
    
    public static EntityManagerFactory getInstance(){
    if(factory==null){
    try{
    Class.forName("com.mysql.jdbc.Driver");
    factory = Persistence.createEntityManagerFactory("vinixworld_vTestServer_war_1.0-SNAPSHOTPU");
    }catch(ClassNotFoundException e){
            System.out.println(e.getStackTrace());
        }
    }
    return factory;
  }
}
