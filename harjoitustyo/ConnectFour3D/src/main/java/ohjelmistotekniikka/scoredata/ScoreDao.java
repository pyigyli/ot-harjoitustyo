
package ohjelmistotekniikka.scoredata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
* Class is used in conjunction with the Database and Score
* to store and retrieve scores from previous games.
*/
public class ScoreDao implements Dao<Score, Integer> {
    
    private Database database;

    /**
    * Method initializes the database.
    * 
    * @param    database    the database to be used
    */
    public ScoreDao(Database database) {
        this.database = database;
    }
    
    /**
    * Method fixes the database in case it throws SQLexeption.
    * 
    * @throws   java.sql.SQLException   invalid SQL-command
    */
    public void initialize() throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("CREATE TABLE Score ("
                                                    + "id int, "
                                                    + "player1 varchar(10), "
                                                    + "player2 varchar(10), "
                                                    + "player3 varchar(10), "
                                                    + "player4 varchar(10), "
                                                    + "winner varchar(10));");
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    @Override
    public List<Score> findAll() throws SQLException {
        List<Score> scores = new ArrayList<>();
        Connection conn = database.getConnection();
        ResultSet result = conn.prepareStatement("SELECT * FROM Score ORDER BY id DESC;").executeQuery();
        while (result.next()) {
            scores.add(new Score(result.getString("player1"), result.getString("player2"), 
                                result.getString("player3"), result.getString("player4"), 
                                result.getString("winner")));
        }
        conn.close();
        return scores;
    }

    @Override
    public void save(Score s) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Score(player1, player2, player3, player4, winner) VALUES(?,?,?,?,?);");
        stmt.setString(1, s.getPlayer1());
        stmt.setString(2, s.getPlayer2());
        stmt.setString(3, s.getPlayer3());
        stmt.setString(4, s.getPlayer4());
        stmt.setString(5, s.getWinner());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    @Override
    public Score findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void update(Score object) throws SQLException {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported.");
    }
}