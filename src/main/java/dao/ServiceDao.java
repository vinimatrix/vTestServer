/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import util.ConectionDB;

/**
 *
 * @author vinicio.mendez
 */
public interface ServiceDao {
    public EntityManager em = ConectionDB.getInstance().createEntityManager();
}

