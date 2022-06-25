package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.LogInParams;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManagerDAO {
    public User login(LogInParams logInParams);
    public int addUser(User user);
    public User getUser(String username);
    public User updateUser(String name, String email, String password);
    public void deleteUser(int employeeID);
    public List<User> getUsers();
    //public List <User> getEmployeeByDept(int deptId);
    public User buyItem (String i, String username);
    public void updateUserLanguage(String username, String language);

    public List<String> getInventory(String username);

    public Item getItem(String name);
}
