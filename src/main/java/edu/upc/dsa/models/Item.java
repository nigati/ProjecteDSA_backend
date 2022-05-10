package edu.upc.dsa.models;

public class Item {
    String name;
    String description;
    double coins;

    public Item() {
    }

    public Item(String name, String description, double coins) {
        this();
        this.setName(name);
        this.setDescription(description);
        this.setCoins(coins);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }
}