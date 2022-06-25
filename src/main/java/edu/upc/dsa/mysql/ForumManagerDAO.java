package edu.upc.dsa.mysql;
import edu.upc.dsa.models.ForumMessage;

import java.util.List;
public interface ForumManagerDAO {

    void addEntry (String playerName, String Message);
    List<ForumMessage> getAllEntries ();
}