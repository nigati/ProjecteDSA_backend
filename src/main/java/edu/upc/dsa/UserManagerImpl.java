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
        logger.info("Trying to add a new User ");
        User aux = hmUsers.get(user.getUsername());//Comprobamos que no este ya registrado
        if (aux == null) //Si no esta en el hashmap, lo añadimos
        {
            this.users.add(user);
            this.hmUsers.put(user.getUsername(), user);
            logger.info("new User added");

            for (User u:users){
                logger.info(u.getUsername());
                logger.info(u.getPassword());
                logger.info(u.getEmail());
        }
            return user;
        } else //si ya tenemos un usuario registrado, retornamos null
        {
            logger.info("this username is already used");
            return null;
        }
    }

    public User login(String username, String password) {
        User u=hmUsers.get(username);
        if (u!=null) { //try

            if (!password.equals(u.getPassword())) {
                logger.warn("Incorrect password");
                return null;

            }
            else {
                logger.warn("User logged in");
                return u;
            }
        } else //catch (NullPointerException e)
        {
            return null;
        }

    }
    public void logOut (String username){

    }

    public List<Item> catalogoTienda() {
        if (items.size()==0)
        {
            items.add(new Item("Vida extra", "Pocion para una vida extra", 100, "https://elescribiente.com/wp-content/uploads/sites/10/extra-life.jpg"));
            items.add(new Item("Sierra", "Sierra que corta mucho", 75, "https://static3.depositphotos.com/1002997/156/i/450/depositphotos_1563641-stock-photo-hand-saw.jpg"));
            items.add(new Item("Escudo", "Proteccion extra", 230, "https://us.123rf.com/450wm/yupiramos/yupiramos1801/yupiramos180111894/93608744-ilustra%C3%A7%C3%A3o-em-vetor-pixelizada-escudo-e-espadas-de-v%C3%ADdeo-game.jpg"));
            items.add(new Item("Espada", "Espada dorada", 150, "https://us.123rf.com/450wm/robuart/robuart1709/robuart170900639/86688066-dos-espadas-cruzadas-de-oro-sobre-fondo-blanco.jpg?ver=6"));
            items.add(new Item("Comida", "Equivale a media vida", 20, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuBCtDV9gSBcdEjScc5yJibRg5ipAKKJj9fw&usqp=CAU"));
            logger.info("Catalogo cargado");
        }

        return items;


    }

    public List<User> getUsers(){
        return users;
    }
}