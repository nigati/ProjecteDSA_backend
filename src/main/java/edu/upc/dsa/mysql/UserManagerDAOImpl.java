package edu.upc.dsa.mysql;

import edu.upc.dsa.UserManager;
import edu.upc.dsa.models.Item;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.LogInParams;
import edu.upc.dsa.models.User;
//import jdk.jpackage.internal.Log;
import org.apache.log4j.Logger;

public class UserManagerDAOImpl implements UserManagerDAO {
    private static UserManagerDAO instance;

    final static Logger logger = Logger.getLogger(UserManagerDAOImpl.class);

    private UserManagerDAOImpl() {

    }

    public static UserManagerDAO getInstance() {
        if (instance == null) instance = new UserManagerDAOImpl();
        return instance;
    }

    public int size() {

        return 0;
    }

    public void addUser(User user) {
        logger.info("Trying to add a new User ");
        Session session = null;
        List<Item> items;
        try{
            session = FactorySession.openSession();

            session.save(user);
            /*boolean alreadyreg =session.isUserRegistered(User.class, user);
            if (!alreadyreg){session.save(user);}
            else {logger.warn("User is already registered");}*/


        }
        catch (Exception e){
            logger.error("Something happened trying to open the session: "+e.getMessage());
        }

       finally{
           session.close();
        }
    }

    @Override
    public User getUser(String username) {

        Session session = null;
        User user1 = null;
        try {
            session = FactorySession.openSession();
            user1 = (User)session.get(User.class, "USERNAME", username);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return user1;

    }

    @Override
    public User updateUser(String name, String email, String password) {
        return null;
    }

    @Override
    public void deleteUser(int employeeID) {

    }

    public User login(LogInParams logInParams) {
        Session session = FactorySession.openSession();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username", logInParams.getUsername());
        hashMap.put("password", logInParams.getPassword());
        List<Object> l = session.findAll(User.class, hashMap);
        if(l.size()==1){
            logger.info("Correct login "+logInParams.getUsername());
            User u = (User) l.get(0);
            //session.update(u); //futuro
            return u;
        }else if(l.size()>1){
            logger.warn("Duplicated user");
            return null;
        }

        logger.warn("Incorrect user name or password");
        return null;
    }
    public void logOut (String username){

    }

    public List<Item> catalogoTienda() {

        return null;
    }

    public List<User> getUsers(){
        return null;
    }
}