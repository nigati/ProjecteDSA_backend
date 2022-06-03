package edu.upc.dsa;

import edu.upc.dsa.models.Stats;
import edu.upc.dsa.models.User;

import java.util.List;

public interface StatsManager {

    public List<Stats> getRanking();
    public int size();
}
