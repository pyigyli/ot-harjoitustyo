
package ohjelmistotekniikka.scoredata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* Class is used in conjunction with the ScoreDao and Score
* to store and retrieve scores from previous games.
*/
public class Database {
    
    private String databaseAddress;

    /**
    * Method creates a new score containing names of all players
    * in the game and the winner.
    * 
    * @param    databaseAddress the location of database-file
    * 
    * @throws   ClassNotFoundException  database-file is not
    *                                   found
    */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
    * Method creates a connection to the database-file, making
    * it possible to store and retrieve scores with SQL-commands.
    * 
    * @return   the connection to the database
    * 
    * @throws   SQLException    invalid SQL-command
    */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
}
