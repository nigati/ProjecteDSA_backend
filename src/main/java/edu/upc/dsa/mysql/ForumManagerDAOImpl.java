package edu.upc.dsa.mysql;

import edu.upc.dsa.models.ForumMessage;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class ForumManagerDAOImpl implements ForumManagerDAO{

    private static ForumManagerDAOImpl manager;

    final static Logger logger = Logger.getLogger(UserManagerDAO.class);

    private ForumManagerDAOImpl(){
    }

    public static ForumManagerDAOImpl getInstance(){
        if(manager==null){
            logger.info("New instance edu.upc.dsa.GameManagerDAOImpl");
            manager = new ForumManagerDAOImpl();
        }
        return manager;
    }

    public static void delete(){
        manager = null;
        logger.info("Instance ForumManagerDAOImpl deleted");
    }

    @Override
    public void addEntry(String username, String Message) {
        Session session = null;
        try{
            session = FactorySession.openSession();
            logger.info("New entry by user " + username + " added");
            List<Object> allMessages = session.findAll(ForumMessage.class);
            Integer current_seq = allMessages.size();
            ForumMessage entry = new ForumMessage(username, Message, current_seq);
            session.save(entry);
            logger.info("Forum message added successfully.");}
        catch (Exception e){}
    }

    @Override
    public List<ForumMessage> getAllEntries(){
        Session session = null;
        try {
            session = FactorySession.openSession();
            logger.info("Get all forum messages in the database.");
            List<ForumMessage> listMessages = new LinkedList<>();
            session.findAll(ForumMessage.class).forEach(entry -> {
                listMessages.add((ForumMessage) entry);
            });
            return listMessages;
        }
        catch (Exception e){
            return null;
        }
    }


}