package edu.upc.dsa.mysql;

import edu.upc.dsa.models.*;

import java.util.List;

public interface UserManagerDAO {
    public User login(LogInParams logInParams);
    public int addUser(User user);
    public User getUser(String username);
    public User updateUser(String name, String email, String password);
    public int deleteUser(String username);
    public List<User> getUsers();
    //public List <User> getEmployeeByDept(int deptId);
    public User buyItem (String i, String username);
    public void updateUserLanguage(String username, String language);

    public int addIssue(Issue issue);

    public List<Inventory> getInventory(String username);
    public void useItemInGame(String name, String username);

    public Item getItem(String name);

    public void updateUserUsername(String username, String new_username);
    public void updateUserPassword(String username, String new_password);
    public void updateUserEmail(String username, String new_email);

}
