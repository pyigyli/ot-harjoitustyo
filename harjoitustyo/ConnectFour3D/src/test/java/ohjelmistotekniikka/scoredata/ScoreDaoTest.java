
package ohjelmistotekniikka.scoredata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ScoreDaoTest {
    
    // When running the tests, a temporary database is created and used.
    
    String databaseFile;
    Path path;
    Database database;
    ScoreDao scoreDao;
    
    @Before
    public void setUp() throws ClassNotFoundException, IOException, SQLException {
        // Ensure that the temporary database doesn't overwrite any existing file
        int i = 0;
        while (true) {
            databaseFile = "TestScores" + i + ".db";
            path = Paths.get("./" + databaseFile);
            File file = new File(path.toUri());
            if (!file.exists()) {
                break;
            }
            i++;
        }
        database = new Database("jdbc:sqlite:" + databaseFile);
        scoreDao = new ScoreDao(database);
    }
    
    @After
    public void deleteTemporaryFiles() throws IOException {
        Files.delete(path);
    }
    
    @Test
    public void saveAndFindAllWorks() throws SQLException, IOException {
        scoreDao.initialize();
        Score score1 = new Score("1", "2", "3", "4", "5");
        Score score2 = new Score("a", "b", "c", "d", "e");
        scoreDao.save(score1);
        scoreDao.save(score2);
        assertEquals("a", scoreDao.findAll().get(1).getPlayer1());
    }
}