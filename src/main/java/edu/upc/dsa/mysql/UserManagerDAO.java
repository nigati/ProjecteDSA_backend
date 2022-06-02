package edu.upc.dsa.mysql;

import edu.upc.dsa.models.LogInParams;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManagerDAO {
    public User login(LogInParams logInParams);
    public void addUser(User user);
    public User getUser(String username);
    public User updateUser(String name, String email, String password);
    public void deleteUser(int employeeID);
    public List<User> getUsers();
    //public List <User> getEmployeeByDept(int deptId);
}
