
package ohjelmistotekniikka.gamelogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GamelogicTest {
    
    Gamelogic game;
    
    @Before
    public void setUp() {
        game = new Gamelogic();
    }
    
    @Test
    public void newGameChangesTurnToZero() {
        game.setTurn(3);
        game.newGame();
        assertEquals(0, game.getTurn());
    }
    
    @Test
    public void newGameEmptiesBoard() {
        game.setSlot(0, 0, 0, 3);
        game.newGame();
        assertEquals(-1, game.getSlot(0, 0, 0));
    }
    
    @Test
    public void placePieceWorks() {
        game.newGame();
        assertEquals(-1, game.getSlot(0, 0, 0));
        game.setPlayers(1);
        game.placePiece(0, 0, game.getBoard());
        assertEquals(0, game.getSlot(0, 0, 0));
    }
}