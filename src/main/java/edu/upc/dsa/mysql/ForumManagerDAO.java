package edu.upc.dsa.mysql;
import edu.upc.dsa.models.ForumMessage;

import java.util.List;
public interface ForumManagerDAO {
//used to add a forum entry in the database
    void addEntry (String playerName, String Message);
    //returns a list with all the forum messages
    List<ForumMessage> getAllEntries ();
}