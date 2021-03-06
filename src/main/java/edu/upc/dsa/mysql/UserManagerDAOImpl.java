package edu.upc.dsa.mysql;

import edu.upc.dsa.models.*;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

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

    public int addIssue(Issue issue) {

        Session session = null;

        try{
            session = FactorySession.openSession();
            session.save(issue);
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
        Session session = null;
        try{
        session = FactorySession.openSession();
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
            return null;}
        }
        catch (Exception e){
            logger.error("Something went wrong:" +e.getMessage());
        }
        finally {
            session.close();
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
        Boolean repetido = false;

        try {
            session = FactorySession.openSession();
            user0 = (User)session.get(User.class, "USERNAME",username);
            logger.info(user0.getUsername());
            i1 = (Item) session.get(Item.class, "NAME", item);
            List<Inventory> list = new ArrayList<>();

            if (user0.getCoins()>= i1.getCoins())
            {
                int saldo = user0.getCoins()- i1.getCoins();
                session.update(User.class, "COINS", String.valueOf(saldo),"USERNAME",username);
                list = (List<Inventory>)session.getList(Inventory.class, "USERNAME", username);
                int i=0;
                while (i< list.size())
                {
                    if (list.get(i).getName().equals(item))
                    {
                        repetido = true;
                        int qty = list.get(i).getQuantity() +1;
                        session.update2(Inventory.class, "QUANTITY", String.valueOf(qty),"USERNAME",username, "NAME", item);
                    }
                    i++;
                }
                if (repetido == false)
                {
                    session.save(new Inventory(item,username, i1.getUrlPic()));
                    session.update2(Inventory.class, "QUANTITY", String.valueOf(1),"USERNAME",username, "NAME", item);
                }

                user1= (User)session.get(User.class,"USERNAME", username); //sale actualizado
//                logger.info("2 " +user1.getCoins());
//                logger.info(user1.getUsername());

            }
            else
            {
                logger.info("Not enough money to buy item");

            }


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
    public void updateUserLanguage(String username, String language) {

        Session session = null;
        User user = null;
        try{
            logger.info("Updating the user: "+username+" language to:" +language);
            session = FactorySession.openSession();
            session.update(User.class, "LANGUAGE", language,"USERNAME",username);
        }
        catch (Exception e){
            logger.error("Error: "+e.getMessage());
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
            logger.info("Updating the user: "+username+" username to:" +new_username);
            session = FactorySession.openSession();
            session.update(User.class, "USERNAME", new_username,"USERNAME",username);
        }
        catch (Exception e){
            logger.error("Error: " +e.getMessage());
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
            logger.info("Updating the user: "+username+" password to:" +new_password);
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
            logger.info("Updating the user: "+username+" email to:" +new_email);
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

    public void useItemInGame(String name, String username)
    {
        Session session = null;
        User user = null;
        try{
            session = FactorySession.openSession();
            //Item i = (Item)session.get(Item.class, "NAME", name);
            Inventory inv = (Inventory)session.get(Inventory.class, "NAME", name);
            String qty = String.valueOf(inv.getQuantity()-1);
            logger.info("New quantity is :" + qty);
            if (inv.getQuantity()-1>=0)
            {
                session.update2(Inventory.class, "QUANTITY",qty,"NAME",name, "USERNAME", username);
            }
            else
            {
                logger.info("No tienes este item, no puedes usarlo");
            }

        }
        catch (Exception e){
            logger.error("Error");
        }
        finally {
            session.close();
        }
    }

}