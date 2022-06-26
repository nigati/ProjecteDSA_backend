package edu.upc.dsa.models;

public class Inventory {
    public String name; // name of the item
    public String username; //username of the player
    public int quantity;
    public Inventory() {}
    public Inventory(String name, String username)
    {
        this.name = name;
        this.username = username;
        //this.quantity=1;
    }

    public String getName()
    {
        return name;
    }

    public String getUsername()
    {
        return username;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setQuantity(int quantity){ this.quantity=quantity;}
}
