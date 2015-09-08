/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.daoImp;

import dao.ChatMsjDao;
import entityes.ChatMsj;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vinicio.mendez
 */
public class ChatMsjDaoImp implements ChatMsjDao{

    @Override
    public void save(ChatMsj object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    @Override
    public void delete(ChatMsj object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(ChatMsj object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChatMsj findbyID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ChatMsj> getAll() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return em.createNamedQuery("ChatMsj.findAll").getResultList();
    }
    public List<ChatMsj> getLasts() throws ParseException {
     Long minutesAgo = new Long(5);
Date date = new Date();
Date dateIn_X_MinAgo = new Date (date.getTime() - minutesAgo*60*1000);

       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return em.createNamedQuery("ChatMsj.findLasts").setParameter("date", dateIn_X_MinAgo).getResultList();
    }
    
}
