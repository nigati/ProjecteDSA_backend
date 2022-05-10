package edu.upc.dsa;

import edu.upc.dsa.models.Track;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {


    public User addUser(User user);
    public boolean login(String username, String password);
    public int size();
}
