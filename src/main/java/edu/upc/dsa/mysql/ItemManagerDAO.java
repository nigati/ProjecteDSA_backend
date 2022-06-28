package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Item;

import java.util.List;

public interface ItemManagerDAO {
    //used to get an item with its name
    public Item getItem (String name);
    //used to get all items
    public List<Item> getAll ();
    //used to get all items of a player

}
