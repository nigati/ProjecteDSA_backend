package edu.upc.dsa;

import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {


    public User addUser(User user);
    public User login(String username, String password);
    public void logOut(String username) throws UserNotFoundException;
    public int size();
    public List<Item> catalogoTienda ();
    public List<User> getUsers();

}
