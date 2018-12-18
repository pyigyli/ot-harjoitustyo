
package ohjelmistotekniikka.scoredata;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ScoreTest {
    
    Score score;
    
    @Before
    public void setUp() {
        score = new Score("Player 1", "Player 2", "Player 3", "Player 4", "Winner");
    }
    
    @Test
    public void getPlayer1Works() {
        assertEquals("Player 1", score.getPlayer1());
    }
    
    @Test
    public void getPlayer2Works() {
        assertEquals("Player 2", score.getPlayer2());
    }
    
    @Test
    public void getPlayer3Works() {
        assertEquals("Player 3", score.getPlayer3());
    }
    
    @Test
    public void setPlayer3Works() {
        score.setPlayer3("test");
        assertEquals("test", score.getPlayer3());
    }
    
    @Test
    public void getPlayer4Works() {
        assertEquals("Player 4", score.getPlayer4());
    }
    
    @Test
    public void setPlayer4Works() {
        score.setPlayer4("test");
        assertEquals("test", score.getPlayer4());
    }
    
    @Test
    public void getWinnerWorks() {
        assertEquals("Winner", score.getWinner());
    }
    
    @Test
    public void setWinnerWorks() {
        score.setWinner("test");
        assertEquals("test", score.getWinner());
    }
}