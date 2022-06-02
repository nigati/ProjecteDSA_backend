package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Item;

import java.util.List;

public interface ItemManagerDAO {
    public Item getItem (String name);
    public List<Item> getAll ();
    public List<Item> getAllItemsFromPlayer (String username);
    //El item es "compra" en la funció ItemService.
    void addItemToPlayer (String itemname, String username);


}
