package edu.upc.dsa.mysql;

import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close();
    void saveUser(Object entity);
    Object get(Class theClass, int ID);
    void update(Object object);
    void delete(Object object);
    boolean isUserRegistered(Class class1, User user);
    List<Object> findAll(Class theClass);
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
}
