package edu.upc.dsa.mysql;

import edu.upc.dsa.models.Game;

public interface GameManagerDAO {
    //used to save the game progress
    public int saveGame (Game game);
    //used to get the last game of a user to load the progress into unity
    public Game getLastGameOfUser(String username);

}
