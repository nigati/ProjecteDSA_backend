package edu.upc.dsa.models;

public class Item {
    String name;
    String description;
    int coins;
    String urlPic;

    public Item() {
    }

    public Item(String name, String description, int coins, String urlPic) {
        this();
        this.setName(name);
        this.setDescription(description);
        this.setCoins(coins);
        this.setUrlPic(urlPic);
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getUrlPic() {
        return this.urlPic;
    }

    public void setUrlPic(String urlPic) {
        this.urlPic = urlPic;
    }
}