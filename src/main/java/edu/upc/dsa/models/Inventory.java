package edu.upc.dsa.models;

public class Inventory {
    public String name; // name of the item
    public String username; //username of the player
    public int quantity;
    public String urlPic;
    public Inventory() {}
    public Inventory(String name, String username,String urlPic)
    {
        this.name = name;
        this.username = username;
        //this.quantity=1;
        this.urlPic=urlPic;
    }

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

    public String getUrlPic(){ return urlPic;}

    public void setName(String name){
        this.name = name;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setQuantity(int quantity){ this.quantity=quantity;}

    public void setUrlPic(String urlPic){ this.urlPic=urlPic;}
}
