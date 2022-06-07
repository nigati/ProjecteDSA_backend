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
    public Item getItem(String item) {

        Session session = null;
        Item item1 = null;
        try {
            session = FactorySession.openSession();
            item1 = (Item) session.get(Item.class, "NAME", item);
            //logger.info("Get item hecho correctamente!");
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return item1;

    }

    @Override
    public List<Item> getAll() {
        Session session = null;
        try {
            session = FactorySession.openSession();
            logger.info("Get all items");
            List<Item> items = new LinkedList<>();
            session.findAll(Item.class).forEach(item -> items.add((Item) item));
            if (items.size()==0)
            {
                items.add(new Item("Vida extra", "Pocion para una vida extra", 100, "https://elescribiente.com/wp-content/uploads/sites/10/extra-life.jpg"));
                items.add(new Item("Sierra", "Sierra que corta mucho", 75, "https://static3.depositphotos.com/1002997/156/i/450/depositphotos_1563641-stock-photo-hand-saw.jpg"));
                items.add(new Item("Escudo", "Proteccion extra", 230, "https://us.123rf.com/450wm/yupiramos/yupiramos1801/yupiramos180111894/93608744-ilustra%C3%A7%C3%A3o-em-vetor-pixelizada-escudo-e-espadas-de-v%C3%ADdeo-game.jpg"));
                items.add(new Item("Espada", "Espada dorada", 150, "https://us.123rf.com/450wm/robuart/robuart1709/robuart170900639/86688066-dos-espadas-cruzadas-de-oro-sobre-fondo-blanco.jpg?ver=6"));
                items.add(new Item("Comida", "Equivale a media vida", 20, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuBCtDV9gSBcdEjScc5yJibRg5ipAKKJj9fw&usqp=CAU"));
                logger.info("Catalogo de shop cargado manualmente porque la BBDD estaba vacia");
            }
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
