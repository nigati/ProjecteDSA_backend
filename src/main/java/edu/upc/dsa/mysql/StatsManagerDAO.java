package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Game;
import edu.upc.dsa.models.Stats;

import java.util.List;

public interface StatsManagerDAO {
    public int updateStats (Game game);
    public Stats getStats(String username);
    public List<Stats> getAll();
}
