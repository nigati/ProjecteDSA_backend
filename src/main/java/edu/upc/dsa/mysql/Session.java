package edu.upc.dsa.mysql;

import edu.upc.dsa.models.LogInParams;
import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close();

    void saveUser(Object entity);

    Object get(Class theClass, String key, Object value);

    //Object getS(Class theClass, String name);

    void update(Class theClass, String SET, String valueSET, String WHERE, String valueWHERE);
    void delete(Object object);
    int deleteUser(String username);
    boolean isUserRegistered(Class class1, User user);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap params);
    Object getByUsername(Class theClass, String username);
    List<Object> query(String query, Class theClass, HashMap params);

    void update2(Class theClass, String SET, String valueSET, String WHERE, String valueWHERE, String WHERE2, String valueWHERE2);

    List<Object> getList(Class theClass, String key, Object value);
}
