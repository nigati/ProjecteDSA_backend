package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Game;

public interface GameManagerDAO {
    public int saveGame (Game game);
    public Game getLastGameOfUser(String username);

}
