package edu.upc.dsa;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Stats;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StatsManagerImpl implements StatsManager{
    private static StatsManager instance;
    protected List<Stats> stats;
    private HashMap<String, Stats> hmStats;
    final static Logger logger = Logger.getLogger(StatsManagerImpl.class);


    private StatsManagerImpl() {
        this.stats = new LinkedList<>();
        hmStats = new HashMap<String, Stats>();
    }

    public static StatsManager getInstance() {
        if (instance == null) instance = new StatsManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.stats.size();
        logger.info("size " + ret);

        return ret;
    }

    public List<Stats> getRanking()
    {
        stats.add(new Stats("claudia",10));
        stats.add(new Stats("barto",23));
        stats.add(new Stats("nico",8));

        stats.sort(Comparator.comparingDouble(Stats::getPoints).reversed());
        logger.info("Estadisticas cargadas completamente");
        return stats;

    }
}
