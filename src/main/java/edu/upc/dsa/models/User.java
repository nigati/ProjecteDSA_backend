package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class User{

    private String username;
    private int id;
    private String email;
    private String password;
    private int coins;
    //private List<Item> inventario;
    public User() {
    }

    public User(String name, String email, String password) {
        this();
        this.setUsername(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setCoins(5000);
        //this.inventario=new ArrayList<>();
    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoins() {return coins;}
    public void setCoins(int coins) {this.coins = coins;}

    /*public List<Item> getInventario() {
        return this.inventario;
    }
    public void setInventario(List<Item> inventario) {
        this.inventario = inventario;
    }*/

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
}