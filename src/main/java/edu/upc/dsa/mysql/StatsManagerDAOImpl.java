package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Stats;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StatsManagerDAOImpl implements StatsManagerDAO {
    private static StatsManagerDAO instance;

    final static Logger logger = Logger.getLogger(StatsManagerDAOImpl.class);

    private StatsManagerDAOImpl() {

    }

    public static StatsManagerDAO getInstance() {
        if (instance == null) instance = new StatsManagerDAOImpl();
        return instance;}

    @Override
    public int updateStats(Game game) {
        return 0;
    }

    @Override
    public Stats getStats(String username) {
        logger.info("Trying to get stats of: " + username);
        Session session = null;
        Stats stats = null;
        try {
            session = FactorySession.openSession();
            stats = (Stats)session.get(Stats.class, "USERNAME", username);
            return stats;
        }
        catch (Exception e) {
            logger.error("Something went wrong: "+e.getMessage());
            return null;
        }
        finally {
            session.close();
        }


    }

    @Override
    public List<Stats> getAll() {
        logger.info("Getting the stats of all players");
        Session session = null;
        List<Stats> stats= new ArrayList<>();
        try{
            session = FactorySession.openSession();
            stats =(List<Stats>)session.findAll(Stats.class);
            stats.sort(Comparator.comparingDouble(Stats::getTime).reversed());
            return stats ;
        } catch (Exception e) {
            logger.error("Something happened trying to open the session: " + e.getMessage());
            return null;
        }
    }
}
