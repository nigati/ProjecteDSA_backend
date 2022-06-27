package edu.upc.dsa;

import edu.upc.dsa.models.Stats;
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
        stats.add(new Stats("claudia",10,"https://www.nationalgeographic.com.es/medio/2018/02/27/playa-de-isuntza-lekeitio__1280x720.jpg"));
        stats.add(new Stats("barto",23,"https://t1.ea.ltmcdn.com/es/posts/7/1/5/los_35_animales_mas_tiernos_del_mundo_24517_orig.jpg"));
        stats.add(new Stats("nico",8,"https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png"));

        stats.sort(Comparator.comparingDouble(Stats::getTime).reversed());
        logger.info("Estadisticas cargadas completamente");
        return stats;

    }
}
