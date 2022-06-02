package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Item;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class ItemManagerDAOImpl implements ItemManagerDAO {
    private static ItemManagerDAO instance;

    final static Logger logger = Logger.getLogger(UserManagerDAOImpl.class);

    private ItemManagerDAOImpl() {

    }

    public static ItemManagerDAO getInstance() {
        if (instance == null) instance = new ItemManagerDAOImpl();
        return instance;
    }

    @Override
    public Item getItem(String name) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        Session session = null;
        try {
            session = FactorySession.openSession();
            logger.info("Get all items");
            List<Item> items = new LinkedList<>();
            session.findAll(Item.class).forEach(item -> items.add((Item) item));
            return items;

        } catch (Exception e) {
        }
        return null;
    }
    @Override
    public List<Item> getAllItemsFromPlayer(String username) {
        return null;
    }

    @Override
    public void addItemToPlayer(String itemname, String username) {

    }
}
