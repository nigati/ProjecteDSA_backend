package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Stats;

import java.util.List;

public interface StatsManagerDAO {
    public void addStats (Game game);
    public Stats getStats(String username);
    public List<Stats> getAllSortedByTime();
    public List<Stats> getAllSortedByKills();
}
