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
    public void addStats(Game game) {

        Session session = null;
//        Stats stats = null;
//        Stats stats1 = null;

        try {
            session = FactorySession.openSession();
            //stats = (Stats)session.get(Stats.class, "USERNAME", game.getUsername());
            Stats stats2 = new Stats(game.getUsername(),game.getTime(), game.getKills(), game.getLevel());
            session.save(stats2);
//            session.update(Stats.class, "TIME", String.valueOf(game.getTime()), "USERNAME", game.getUsername());
//            session.update(Stats.class, "LEVEL", String.valueOf(game.getLevel()), "USERNAME", game.getUsername());
//            session.update(Stats.class, "ENEMIESKILLED", String.valueOf(game.getKills()), "USERNAME", game.getUsername());
//            stats1 = (Stats)session.get(Stats.class, "USERNAME", game.getUsername());
        }
        catch (Exception e) {
            logger.error("Something went wrong: "+e.getMessage());
        }
        finally {
            session.close();
        }
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
    public List<Stats> getAllSortedByTime() {
        logger.info("Getting the stats of all players and sorting by best times");
        Session session = null;
        List<Stats> stats= new ArrayList<>();
        try{
            session = FactorySession.openSession();
            stats =(List<Stats>)session.findAll(Stats.class);
            stats.sort(Comparator.comparingDouble(Stats::getTime));
            List<Stats> stat1 =stats.subList(0,10);
            return stat1 ;
        } catch (Exception e) {
            logger.error("Something happened trying to open the session: " + e.getMessage());
            return null;
        }
    }

    public List<Stats> getAllSortedByKills()
    {
        logger.info("Getting the stats of all players and sorting by enemies killed");
        Session session = null;
        List<Stats> stats= new ArrayList<>();
        try{
            session = FactorySession.openSession();
            stats =(List<Stats>)session.findAll(Stats.class);
            stats.sort(Comparator.comparingDouble(Stats::getEnemiesKilled).reversed());
            List<Stats> stat1 = stats.subList(0,10);
            return stat1 ;
        } catch (Exception e) {
            logger.error("Something happened trying to open the session: " + e.getMessage());
            return null;
        }

    }
}
