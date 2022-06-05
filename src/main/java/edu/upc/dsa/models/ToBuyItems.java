package edu.upc.dsa.models;

public class ToBuyItems {
    public String item;
    public String player;

    public ToBuyItems() {}
    public ToBuyItems(String item, String player)
    {
        this.item =item;
        this.player =player;
    }

    public String getItem()
    {
        return item;
    }

    public String getPlayer()
    {
        return player;
    }

    public void setItem(String item){
        this.item = item;
    }

    public void setPlayer(String player)
    {
        this.player = player;
    }
}
