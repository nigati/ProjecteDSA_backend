package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Item;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.list;

public class GameManagerDAOImpl implements GameManagerDAO {
    private static GameManagerDAO instance;

    final static Logger logger = Logger.getLogger(GameManagerDAOImpl.class);

    private GameManagerDAOImpl() {

    }

    public static GameManagerDAO getInstance() {
        if (instance == null) instance = new GameManagerDAOImpl();
        return instance;
    }

    @Override
    public int saveGame(Game game) {
        logger.info("Trying to add a new Game ");
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.save(game);
            logger.info("The game with id: " + game.getId() + " is being inserted");
            return 1;
        } catch (Exception e) {
            logger.error("Something happened trying to open the session: " + e.getMessage());
            return 0;
        }
    }

    @Override

    public Game getLastGameOfUser(String username) {
        logger.info("Getting the last game of: "+username);
        Session session = null;
        List<Game> list= new ArrayList<>();
        try{
            session = FactorySession.openSession();
            list =(List<Game>)session.getList(Game.class, "USERNAME", username);
            int i=list.size();
            Game lastGame = list.get(i-1);
            return lastGame ;
        } catch (Exception e) {
            logger.error("Something happened trying to open the session: " + e.getMessage());
            return null;
        }
    }
}
