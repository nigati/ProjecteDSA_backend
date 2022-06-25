package edu.upc.dsa.mysql;

import edu.upc.dsa.UserManager;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;


import java.util.ArrayList;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

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

    public int addUser(User user) {
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
            return 0;
        }

       finally{
           session.close();

        }
        return 1;
    }

    @Override
    public User getUser(String username) {
        logger.info("Trying to get user with username: " + username);
        Session session = null;
        User user1 = null;
        try {
            session = FactorySession.openSession();
            user1 = (User)session.get(User.class, "USERNAME", username);
        }
        catch (Exception e) {
            logger.error("Something went wrong: "+e.getMessage());
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
    public int deleteUser(String username) {
        int i=0;
        Session session = null;

        try{

            session = FactorySession.openSession();
            logger.info("Deleting user with username: "+username);
            i=session.deleteUser(username);

        }
        catch (Exception e){
            logger.error("Error");
            i=0;
        }
        finally {
            session.close();
        }
        return i;
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



    public User buyItem (String item, String username) //We will update the coins after buying something
    {
        Session session = null;
        User user1 = null;
        Item i1=null;
        User user0 = null;
        User user2=null;

        try {
            session = FactorySession.openSession();
            user0 = (User)session.get(User.class, "USERNAME",username);
            logger.info(user0.getUsername());
            i1 = (Item) session.get(Item.class, "NAME", item);

            if (user0.getCoins()>= i1.getCoins())
            {
                int saldo = user0.getCoins()- i1.getCoins();
                session.update(User.class, "COINS", String.valueOf(saldo),"USERNAME",username);
                session.save(new Inventory(item,username));
                user1= (User)session.get(User.class,"USERNAME", username);
                logger.info("2 " +user1.getCoins());
                logger.info(user1.getUsername());

            }
            else
            {
                logger.info("Not enough money to buy item");

            }


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
    public void updateUserLanguage(String username, String language) {
        Session session = null;
        User user = null;
        try{
            session = FactorySession.openSession();
            session.update(User.class, "LANGUAGE", language,"USERNAME",username);
        }
        catch (Exception e){
            logger.error("Error");
        }
        finally {
            session.close();
        }

    }


    public List<Inventory> getInventory(String username){
        Session session = null;

        List<Inventory> list= new ArrayList<>();
        //logger.info("Intentando encontrar el inventario de " + username);

        try{
            session = FactorySession.openSession();
            //logger.info("BBDD abierta");
            list =(List<Inventory>)session.getList(Inventory.class, "USERNAME", username);
            //logger.info("Primer item de "+ username + " es " + list.get(0).getName());
        }
        catch (Exception e) {
            logger.error("Error en el inventario");
        }
            return list;

        }

    @Override
    public void updateUserUsername(String username, String new_username) {
        Session session = null;
        User user = null;
        try{
            session = FactorySession.openSession();
            session.update(User.class, "USERNAME", new_username,"USERNAME",username);
        }
        catch (Exception e){
            logger.error("Error");
        }
        finally {
            session.close();
        }

    }

    @Override
    public void updateUserPassword(String username, String new_password) {
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            session.update(User.class, "PASSWORD", new_password, "USERNAME", username);
        } catch (Exception e) {
            logger.error("Error");
        } finally {
            session.close();
        }
    }



    public Item getItem(String name)
    {
        logger.info("Trying to get item with name: " + name);
        Session session = null;
        Item item1 = null;
        try {
            session = FactorySession.openSession();
            item1 = (Item)session.get(Item.class, "NAME", name);
            logger.info(item1.getName());
        }
        catch (Exception e) {
            logger.error("Something went wrong: " + e.getMessage());
        }
        return item1;

    }

    @Override
    public void updateUserEmail(String username, String new_email) {
        Session session = null;
        User user = null;
        try{
            session = FactorySession.openSession();
            session.update(User.class, "EMAIL", new_email,"USERNAME",username);
        }
        catch (Exception e){
            logger.error("Error");
        }
        finally {
            session.close();
        }

    }

}