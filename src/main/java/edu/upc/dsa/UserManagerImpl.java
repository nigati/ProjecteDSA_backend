package edu.upc.dsa;

import edu.upc.dsa.models.Item;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

public class UserManagerImpl implements UserManager {
    private static UserManager instance;
    protected List<User> users;
    private HashMap<String, User> hmUsers;
    protected List<Item> items;
    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private UserManagerImpl() {
        this.users = new LinkedList<>();
        this.items = new LinkedList<>();
        hmUsers = new HashMap<String, User>();
    }

    public static UserManager getInstance() {
        if (instance == null) instance = new UserManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.users.size();
        logger.info("size " + ret);

        return ret;
    }

    public User addUser(User user) {
        logger.info("new User " + user);

        this.users.add(user);
        this.hmUsers.put(user.getUsername(), user);
        logger.info("new User added");
        return user;
    }

    public boolean login(String username, String password) {
        User u;
        try {
            u = hmUsers.get(username);
        }
        catch (NullPointerException e) {
            return false;
        }
            if (!password.equals(u.getPassword())) {
                logger.warn("Incorrect password");
                return false;


            }
            return true;
        }

    public List<Item> catalogoTienda ()
    {
        items.add (new Item("Vida extra", "Pocion para una vida extra", 100.0));
        items.add (new Item("Sierra", "Sierra que corta mucho", 75.0));
        items.add (new Item("Escudo", "Proteccion extra", 230.0));
        items.add (new Item("Espada", "Espada dorada", 150.0));
        items.add (new Item("Comida", "Equivale a media vida", 20.0));
        return items;

    }
}